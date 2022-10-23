package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysRoleMenu;

import java.util.List;

/**
 * @author wgy
 * @description 针对表【sys_role_menu(角色菜单关联表)】的数据库操作Service
 * @createDate 2022-10-04 14:29:06
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

  /**
   * 删除关系连接根据菜单id
   *
   * @param menuId 菜单id
   * @return
   * @author wgy
   * @data 2022/10/17
   */
  void deleteByMenuId(String menuId);

  /**
   * 根据角色id删除连接
   *
   * @param roleId 角色id
   * @return
   * @author wgy
   * @data 2022/10/21
   */
  void deleteByRoleId(String roleId);

  /**
   * 通过角色id列表获取角色菜单id
   *
   * @param roleIds 角色id列表
   * @return @return {@link List }<{@link String }>
   * @author wgy
   * @data 2022/10/19
   */
  List<String> getMenuIdsByRoleIds(List<String> roleIds);
}
