package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysUserRole;

import java.util.Set;

/**
 * @author 24709
 * @description 针对表【sys_user_role(用户角色关联表)】的数据库操作Service
 * @createDate 2022-08-27 13:58:57
 */
public interface SysUserRoleService extends IService<SysUserRole> {

  /**
   * 给用户赋予角色
   *
   * @param userId
   * @param roleIds
   */
  void setUserRole(String userId, Set<String> roleIds);

  /**
   * 根据用户ID删除
   *
   * @param userId
   */
  void deleteByUserId(String userId);

  /**
   * 根据角色ID删除
   *
   * @param roleId
   */
  void deleteByRoleId(String roleId);

  /**
   * 根据用户ID查询角色ID集合
   *
   * @param userId
   * @return
   */
  Set<String> selectRoleIdListByUserId(String userId);
}
