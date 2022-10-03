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
import io.github.nginx.ops.server.admin.service.AdminServer;
import io.github.nginx.ops.server.comm.constant.CacheConstants;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.comm.util.json.JsonUtils;
import io.github.nginx.ops.server.system.domain.SysUser;
import io.github.nginx.ops.server.system.domain.dto.SysRoleDTO;
import io.github.nginx.ops.server.system.domain.dto.SysSettingDTO;
import io.github.nginx.ops.server.system.domain.dto.SysUserDTO;
import io.github.nginx.ops.server.system.domain.dto.UserInfo;
import io.github.nginx.ops.server.system.service.SysRoleService;
import io.github.nginx.ops.server.system.service.SysSettingService;
import io.github.nginx.ops.server.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
  private final RedisTemplate redisTemplate;
  private final SysUserService sysUserService;
  private final SysRoleService sysRoleService;
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

  public static void main(String[] args) {
    //
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println("encoder.encode(\"123456\") = " + encoder.encode("123456"));
    System.out.println(
        "encoder.matches(\"123456\", \"$2a$10$zyGraLskfQl.T5oncHzr9uvoKcX5zxak3NZe/MMSgAzn1poRLRv0u\") = "
            + encoder.matches(
                "123456", "$2a$10$zyGraLskfQl.T5oncHzr9uvoKcX5zxak3NZe/MMSgAzn1poRLRv0u"));
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
    redisTemplate.opsForValue().set(cacheKey, verCode, 10, TimeUnit.MINUTES);
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
    if (Boolean.FALSE.equals(redisTemplate.hasKey(cacheKey))) {
      throw new BusinessException(AdminReturnCodeConstant.CAPTCHA_HAS_EXPIRED);
    } else if (!dto.getVerCode()
        .equalsIgnoreCase((String) redisTemplate.opsForValue().get(cacheKey))) {
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
    redisTemplate.delete(cacheKey);
    // 进行登录
    StpUtil.login(
        dto.getLoginName(),
        new SaLoginModel().setDevice(dto.getDevice()).setIsLastingCookie(dto.getRemember()));
    // 第2步，获取 Token  相关参数
    return StpUtil.getTokenInfo();
  }
}
