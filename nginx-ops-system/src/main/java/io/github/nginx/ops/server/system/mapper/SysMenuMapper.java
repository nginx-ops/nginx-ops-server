package io.github.nginx.ops.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.nginx.ops.server.system.domain.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author wgy
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
 * @createDate 2022-10-04 13:58:16 @Entity com.hongye.system.domain.SysMenu
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

  /**
   * 查找菜单根据角色id列表
   *
   * @param roleIds 角色id
   * @return @return {@link Set }<{@link SysMenu }>
   * @author wgy
   * @data 2022/10/04
   */
  Set<String> selectPermByRoleIds(@Param("roleIds") List<String> roleIds);
}
