package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysMenu;
import io.github.nginx.ops.server.system.mapper.SysMenuMapper;
import io.github.nginx.ops.server.system.service.SysMenuService;
import io.github.nginx.ops.server.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author wgy
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2022-10-04 13:58:16
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService {

  private final LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();

  private final SysUserRoleService sysUserRoleService;
  private final SysMenuMapper sysMenuMapper;

  @Override
  public Set<String> getPermsByUserId(String userId) {
    // 获取角色id列表
    Set<String> roleIdList = sysUserRoleService.selectRoleIdListByUserId(userId);
    return sysMenuMapper.selectPermByRoleIds(roleIdList);
  }

  @Override
  public List<SysMenu> getRouterMenuByUserId(String userId) {
    // 获取角色id列表
    Set<String> roleIdList = sysUserRoleService.selectRoleIdListByUserId(userId);
    return sysMenuMapper.selectMenuByRoleIds(roleIdList);
  }
}
