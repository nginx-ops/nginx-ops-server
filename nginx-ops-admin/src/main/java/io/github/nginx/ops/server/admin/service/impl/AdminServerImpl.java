package io.github.nginx.ops.server.admin.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.wf.captcha.SpecCaptcha;
import io.github.nginx.ops.server.admin.constant.AdminReturnCodeConstant;
import io.github.nginx.ops.server.admin.domain.dto.LoginDTO;
import io.github.nginx.ops.server.admin.domain.vo.CaptchaVO;
import io.github.nginx.ops.server.admin.domain.vo.MetaVo;
import io.github.nginx.ops.server.admin.domain.vo.RouterVo;
import io.github.nginx.ops.server.admin.service.AdminServer;
import io.github.nginx.ops.server.comm.constant.CacheConstants;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.comm.util.json.JsonUtils;
import io.github.nginx.ops.server.system.domain.SysMenu;
import io.github.nginx.ops.server.system.domain.SysUser;
import io.github.nginx.ops.server.system.domain.dto.SysRoleDTO;
import io.github.nginx.ops.server.system.domain.dto.SysSettingDTO;
import io.github.nginx.ops.server.system.domain.dto.SysUserDTO;
import io.github.nginx.ops.server.system.domain.dto.UserInfo;
import io.github.nginx.ops.server.system.service.SysMenuService;
import io.github.nginx.ops.server.system.service.SysRoleService;
import io.github.nginx.ops.server.system.service.SysSettingService;
import io.github.nginx.ops.server.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lihao3
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServerImpl implements AdminServer {

  private static final Snowflake SNOWFLAKE = IdUtil.getSnowflake();
  private final StringRedisTemplate stringRedisTemplate;
  private final SysUserService sysUserService;
  private final SysRoleService sysRoleService;
  private final SysMenuService sysMenuService;
  private final SysSettingService sysSettingService;

  private final BCryptPasswordEncoder encoder;

  @Override
  public UserInfo getUserInfo() {
    // 获取当前登录的唯一ID
    String loginName = StpUtil.getLoginIdAsString();
    // 查询用户信息
    SysUser sysUser = sysUserService.getOneByLoginName(loginName);
    SysUserDTO sysUserDTO = BeanUtil.copyProperties(sysUser, SysUserDTO.class);
    List<SysRoleDTO> sysRoleDTOList = sysRoleService.selectSysRoleListByUserId(sysUser.getId());
    List<SysSettingDTO> sysSettingDTOList = sysSettingService.selectByUserId(sysUser.getId());
    // 装配实体类
    UserInfo userInfo =
        UserInfo.builder()
            .sysUser(sysUserDTO)
            .sysRoleList(sysRoleDTOList)
            .sysSettingList(sysSettingDTOList)
            .build();
    // 存入缓存
    StpUtil.getSession().set(CacheConstants.USERINFO, JsonUtils.toJSONString(userInfo));
    return userInfo;
  }

  @Override
  public void logout() {
    StpUtil.logout();
  }

  @Override
  public CaptchaVO captcha() {
    String id = SNOWFLAKE.nextIdStr();
    String cacheKey =
        CacheConstants.APP_NAME
            + CacheConstants.SEPARATOR
            + CacheConstants.CAPTCHA
            + CacheConstants.SEPARATOR
            + id;
    SpecCaptcha specCaptcha = new SpecCaptcha();
    String verCode = specCaptcha.text().toLowerCase();
    // 存入redis并设置过期时间为10分钟
    stringRedisTemplate.opsForValue().set(cacheKey, verCode, 10, TimeUnit.MINUTES);
    // 将key和base64返回给前端
    return CaptchaVO.builder().id(id).image(specCaptcha.toBase64()).build();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public SaTokenInfo login(LoginDTO dto, HttpServletRequest request) {
    // 判断验证码是否正确
    String cacheKey =
        CacheConstants.APP_NAME
            + CacheConstants.SEPARATOR
            + CacheConstants.CAPTCHA
            + CacheConstants.SEPARATOR
            + dto.getVerId();
    if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(cacheKey))) {
      throw new BusinessException(AdminReturnCodeConstant.CAPTCHA_HAS_EXPIRED);
    } else if (!stringRedisTemplate
        .opsForValue()
        .get(cacheKey)
        .equalsIgnoreCase(dto.getVerCode())) {
      throw new BusinessException(AdminReturnCodeConstant.CAPTCHA_ERROR);
    }
    SysUser sysUser = sysUserService.getOneByLoginName(dto.getLoginName());
    if (ObjectUtil.isEmpty(sysUser)
        || Boolean.FALSE.equals(encoder.matches(dto.getPassword(), sysUser.getPassword()))) {
      throw new BusinessException(AdminReturnCodeConstant.USER_DOES_NOT_EXIST);
    }
    if (Boolean.FALSE.equals(sysUser.getIsEnable())) {
      throw new BusinessException(AdminReturnCodeConstant.USER_NOT_ENABLE);
    }
    // 修改登录时间
    sysUserService.updateById(
        SysUser.builder()
            .id(sysUser.getId())
            .loginIp(ServletUtil.getClientIP(request))
            .loginDate(new Date())
            .build());
    // 删除验证码
    stringRedisTemplate.delete(cacheKey);
    // 进行登录
    StpUtil.login(
        dto.getLoginName(),
        new SaLoginModel().setDevice(dto.getDevice()).setIsLastingCookie(dto.getRemember()));
    // 第2步，获取 Token  相关参数
    return StpUtil.getTokenInfo();
  }

  @Override
  public List<RouterVo> getRouters() {
    String loginName = StpUtil.getLoginIdAsString();
    List<SysMenu> sysMenuList = sysMenuService.getRouterMenuByUserId(loginName);

    // 创建路由列表
    List<RouterVo> routers = new ArrayList<>();

    this.getRouters(routers, sysMenuList);

    return routers;
  }

  // 递归构建路由列表
  private void getRouters(List<RouterVo> routerVoList, List<SysMenu> sysMenuList) {
    sysMenuList.forEach(
        item -> {
          if (!item.getIsFrame()) {
            RouterVo routerVo = new RouterVo();
            // 非外链
            routerVo.setRedirect("noRedirect");
            // 父菜单
            if ("0".equals(item.getParentId())) {
              routerVo.setComponent("Layout");
            } else {
              routerVo.setComponent(item.getComponent());
            }
            routerVo.setName(
                item.getPath().substring(0, 1).toUpperCase() + item.getPath().substring(1));
            routerVo.setPath("/" + item.getPath());
            routerVo.setHidden(!item.getIsEnable());
            if (item.getChildrenList() != null) {
              routerVo.setAlwaysShow(item.getChildrenList().size() > 1);
            }
            MetaVo metaVo = new MetaVo();
            metaVo.setIcon(item.getIcon());
            metaVo.setTitle(item.getMenuName());
            metaVo.setNoCache(item.getIsCache());
            routerVo.setMeta(metaVo);

            // 子路由
            if (item.getChildrenList() != null && item.getChildrenList().size() > 0) {
              List<RouterVo> childrenRouter = new ArrayList<>();
              this.getRouters(childrenRouter, item.getChildrenList());
              routerVo.setChildren(childrenRouter);
            }
            routerVoList.add(routerVo);
          } else {
            RouterVo routerVo = new RouterVo();
            // 外链
            routerVo.setComponent("Layout");
            routerVo.setName(item.getPath());
            routerVo.setPath(item.getPath());
            routerVo.setHidden(!item.getIsEnable());
            MetaVo metaVo = new MetaVo();
            metaVo.setIcon(item.getIcon());
            metaVo.setTitle(item.getMenuName());
            metaVo.setNoCache(item.getIsCache());
            metaVo.setLink(item.getPath());
            routerVo.setMeta(metaVo);
            routerVoList.add(routerVo);
          }
        });
  }
}
