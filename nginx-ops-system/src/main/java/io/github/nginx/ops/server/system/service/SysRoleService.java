package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysRole;
import io.github.nginx.ops.server.system.domain.dto.SysRoleDTO;
import io.github.nginx.ops.server.system.domain.query.SysRoleQuery;

import java.util.List;

/**
 * @author wgy
 * @description 针对表【sys_role(角色表)】的数据库操作Service
 * @createDate 2022-08-27 13:58:57
 */
public interface SysRoleService extends IService<SysRole> {

  /**
   * 根据用户ID查询角色列表
   *
   * @param id
   * @return
   */
  List<SysRoleDTO> selectSysRoleListByUserId(String id);

  /**
   * 新增
   *
   * @param dto
   */
  void save(SysRoleDTO dto);

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
  void update(SysRoleDTO dto);

  /**
   * 列表查询
   *
   * @param query
   * @return
   */
  List<SysRole> list(SysRoleQuery query);

  /**
   * 根据ID查询单条
   *
   * @param id
   * @return
   */
  SysRoleDTO getOne(String id);
}
