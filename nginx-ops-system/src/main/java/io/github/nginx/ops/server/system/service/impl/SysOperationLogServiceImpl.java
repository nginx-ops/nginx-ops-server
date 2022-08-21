package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysOperationLog;
import io.github.nginx.ops.server.system.mapper.SysOperationLogMapper;
import io.github.nginx.ops.server.system.service.SysOperationLogService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【sys_operation_log(操作日志表)】的数据库操作Service实现
 * @createDate 2022-08-21 21:06:44
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog>
    implements SysOperationLogService {}
