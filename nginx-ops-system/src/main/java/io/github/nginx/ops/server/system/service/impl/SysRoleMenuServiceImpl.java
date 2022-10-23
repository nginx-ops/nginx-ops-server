package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysRoleMenu;
import io.github.nginx.ops.server.system.mapper.SysRoleMenuMapper;
import io.github.nginx.ops.server.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wgy
 * @description 针对表【sys_role_menu(角色菜单关联表)】的数据库操作Service实现
 * @createDate 2022-10-04 14:29:06
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
    implements SysRoleMenuService {

  private final LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public void deleteByMenuId(String menuId) {
    queryWrapper.clear();
    queryWrapper.eq(SysRoleMenu::getMenuId, menuId);
    this.remove(queryWrapper);
  }

  @Override
  public void deleteByRoleId(String roleId) {
    queryWrapper.clear();
    queryWrapper.eq(SysRoleMenu::getRoleId, roleId);
    this.remove(queryWrapper);
  }

  @Override
  public List<String> getMenuIdsByRoleIds(List<String> roleIds) {
    queryWrapper.clear();
    queryWrapper.in(SysRoleMenu::getRoleId, roleIds).select(SysRoleMenu::getMenuId);
    List<SysRoleMenu> list = this.list(queryWrapper);
    // 过滤我们想要的字段
    return list.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
  }
}
