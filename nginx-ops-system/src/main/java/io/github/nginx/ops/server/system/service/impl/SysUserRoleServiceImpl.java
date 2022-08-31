package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysUserRole;
import io.github.nginx.ops.server.system.mapper.SysUserRoleMapper;
import io.github.nginx.ops.server.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 24709
 * @description 针对表【sys_user_role(用户角色关联表)】的数据库操作Service实现
 * @createDate 2022-08-27 13:58:57
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
    implements SysUserRoleService {

  private final LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public void setUserRole(String userId, Set<String> roleIds) {
    queryWrapper.clear();
    queryWrapper.eq(SysUserRole::getUserId, userId);
    this.remove(queryWrapper);
    if (ObjectUtil.isAllNotEmpty(userId, roleIds)) {
      List<SysUserRole> sysUserRoleList = new ArrayList<>();
      roleIds.forEach(
          role -> sysUserRoleList.add(SysUserRole.builder().roleId(role).userId(userId).build()));
      this.saveBatch(sysUserRoleList);
    }
  }

  @Override
  public void deleteByUserId(String userId) {
    queryWrapper.clear();
    queryWrapper.eq(SysUserRole::getUserId, userId);
    this.remove(queryWrapper);
  }

  @Override
  public void deleteByRoleId(String roleId) {
    queryWrapper.clear();
    queryWrapper.eq(SysUserRole::getRoleId, roleId);
    this.remove(queryWrapper);
  }

  @Override
  public Set<String> selectRoleIdListByUserId(String userId) {
    queryWrapper.clear();
    queryWrapper.eq(SysUserRole::getUserId, userId).select(SysUserRole::getRoleId);
    List<SysUserRole> userRoleList = this.list(queryWrapper);
    return userRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toSet());
  }
}
