package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysRoleMenu;
import io.github.nginx.ops.server.system.mapper.SysRoleMenuMapper;
import io.github.nginx.ops.server.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author wgy
 * @description 针对表【sys_role_menu(角色菜单关联表)】的数据库操作Service实现
 * @createDate 2022-10-04 14:29:06
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
    implements SysRoleMenuService {}
