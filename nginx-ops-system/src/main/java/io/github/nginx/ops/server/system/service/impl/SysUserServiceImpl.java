package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.system.domain.SysUser;
import io.github.nginx.ops.server.system.domain.dto.SysRoleDTO;
import io.github.nginx.ops.server.system.domain.dto.SysUserRoleDTO;
import io.github.nginx.ops.server.system.domain.query.SysUserQuery;
import io.github.nginx.ops.server.system.mapper.SysUserMapper;
import io.github.nginx.ops.server.system.service.SysRoleService;
import io.github.nginx.ops.server.system.service.SysSettingService;
import io.github.nginx.ops.server.system.service.SysUserRoleService;
import io.github.nginx.ops.server.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lihao3
 * @date 2022/8/24 9:41
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService {

  private final LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();

  private final SysUserRoleService sysUserRoleService;
  private final SysRoleService sysRoleService;
  private final SysSettingService sysSettingService;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(SysUserRoleDTO dto) {
    queryWrapper.clear();
    queryWrapper.eq(SysUser::getLoginName, dto.getLoginName());
    if (this.count(queryWrapper) > 0) {
      throw new BusinessException("登录名重复, 请核实后重新提交!");
    }
    SysUser sysUser = BeanUtil.copyProperties(dto, SysUser.class);
    this.save(sysUser);
    sysUserRoleService.setUserRole(
        sysUser.getId(),
        dto.getSysRoleList().stream().map(SysRoleDTO::getId).collect(Collectors.toSet()));
  }

  @Override
  public void delete(String id) {
    SysUser sysUser = this.getById(id);
    // 判断是否修改登录名
    if (ObjectUtil.isNotEmpty(sysUser)) {
      throw new BusinessException("无效的用户ID!");
    }
    this.removeById(id);
    sysUserRoleService.deleteByUserId(id);
  }

  @Override
  public void update(String id, SysUserRoleDTO dto) {
    SysUser sysUser = this.getById(id);
    // 判断是否修改登录名
    if (ObjectUtil.isNotEmpty(sysUser)) {
      throw new BusinessException("无效的用户ID!");
    }
    // 判断是否修改登录名
    if (ObjectUtil.isNotEmpty(dto.getLoginName())) {
      queryWrapper.clear();
      queryWrapper
          .eq(SysUser::getLoginName, dto.getLoginName())
          .ne(SysUser::getLoginName, sysUser.getLoginName());
      if (this.count(queryWrapper) > 0) {
        throw new BusinessException("登录名重复, 请核实后重新提交!");
      }
    }
    SysUser updateSysUser = BeanUtil.copyProperties(dto, SysUser.class);
    updateSysUser.setId(id);
    this.updateById(updateSysUser);
    sysUserRoleService.setUserRole(
        sysUser.getId(),
        dto.getSysRoleList().stream().map(SysRoleDTO::getId).collect(Collectors.toSet()));
  }

  @Override
  public List<SysUser> list(SysUserQuery query) {
    queryWrapper.clear();
    queryWrapper
        .like(
            ObjectUtil.isNotEmpty(query.getLoginName()),
            SysUser::getLoginName,
            query.getLoginName())
        .like(ObjectUtil.isNotEmpty(query.getNickName()), SysUser::getNickName, query.getNickName())
        .like(ObjectUtil.isNotEmpty(query.getEmail()), SysUser::getEmail, query.getEmail())
        .eq(ObjectUtil.isNotEmpty(query.getIsEnable()), SysUser::getIsEnable, query.getIsEnable())
        .between(
            ObjectUtil.isAllNotEmpty(query.getStartCreatTime(), query.getEndCreatTime()),
            SysUser::getLoginDate,
            query.getStartCreatTime(),
            query.getEndCreatTime())
        .between(
            ObjectUtil.isAllNotEmpty(query.getStartCreatTime(), query.getEndCreatTime()),
            SysUser::getCreateTime,
            query.getStartCreatTime(),
            query.getEndCreatTime());
    return this.list(queryWrapper);
  }


  @Override
  public SysUser getOneByLoginName(String loginName) {
    queryWrapper.clear();
    queryWrapper.eq(SysUser::getLoginName, loginName);
    return this.getOne(queryWrapper);
  }
}
