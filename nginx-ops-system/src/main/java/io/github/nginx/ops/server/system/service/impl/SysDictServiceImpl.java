package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysDict;
import io.github.nginx.ops.server.system.mapper.SysDictMapper;
import io.github.nginx.ops.server.system.service.SysDictService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【sys_dict(系统设置-字典表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:50:43
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict>
    implements SysDictService {}
