package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysUser;
import io.github.nginx.ops.server.system.domain.dto.SysUserRoleDTO;
import io.github.nginx.ops.server.system.domain.query.SysUserQuery;

import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/24 9:41
 */
public interface SysUserService extends IService<SysUser> {

  /**
   * 新增
   *
   * @param dto
   */
  void save(SysUserRoleDTO dto);

  /**
   * 根据ID删除
   *
   * @param id
   */
  void delete(String id);

  /**
   * 根据ID修改
   *
   * @param id
   * @param dto
   */
  void update(String id, SysUserRoleDTO dto);

  /**
   * 查询列表
   *
   * @param query
   * @return
   */
  List<SysUser> list(SysUserQuery query);

  /**
   * 根据用户名查询用户
   *
   * @param loginName
   * @return
   */
  SysUser getOneByLoginName(String loginName);
}
