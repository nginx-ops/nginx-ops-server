package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * @author wgy
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
 * @createDate 2022-10-04 13:58:16
 */
public interface SysMenuService extends IService<SysMenu> {

  /**
   * 通过用户id获取用户权限列表
   *
   * @param userId 用户id
   * @return @return {@link Set }<{@link String }>
   * @author wgy
   * @data 2022/10/04
   */
  Set<String> getPermsByUserId(String userId);

  /**
   * 列表
   *
   * @param userId 用户id
   * @return @return {@link List }<{@link SysMenu }>
   * @author wgy
   * @data 2022/10/05
   */
  List<SysMenu> getRouterMenuByUserId(String userId);
}
