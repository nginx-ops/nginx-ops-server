package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysUserRole;
import io.github.nginx.ops.server.system.mapper.SysUserRoleMapper;
import io.github.nginx.ops.server.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【sys_user_role(用户角色关联表)】的数据库操作Service实现
 * @createDate 2022-08-27 13:58:57
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
    implements SysUserRoleService {}
