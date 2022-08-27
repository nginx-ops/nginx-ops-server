package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysRole;
import io.github.nginx.ops.server.system.mapper.SysRoleMapper;
import io.github.nginx.ops.server.system.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【sys_role(角色表)】的数据库操作Service实现
 * @createDate 2022-08-27 13:58:57
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService {}
