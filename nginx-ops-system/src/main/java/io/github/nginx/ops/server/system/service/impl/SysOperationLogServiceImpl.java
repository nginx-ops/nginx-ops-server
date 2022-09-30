package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysOperationLog;
import io.github.nginx.ops.server.system.domain.query.SysOperationLogQuery;
import io.github.nginx.ops.server.system.mapper.SysOperationLogMapper;
import io.github.nginx.ops.server.system.service.SysOperationLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【sys_operation_log(操作日志表)】的数据库操作Service实现
 * @createDate 2022-08-21 21:06:44
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog>
    implements SysOperationLogService {

  private final LambdaQueryWrapper<SysOperationLog> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public List<SysOperationLog> list(SysOperationLogQuery query) {
    queryWrapper.clear();
    queryWrapper
        .like(ObjectUtil.isNotEmpty(query.getTitle()), SysOperationLog::getTitle, query.getTitle())
        .eq(
            ObjectUtil.isNotEmpty(query.getBusinessType()),
            SysOperationLog::getBusinessType,
            query.getBusinessType())
        .eq(
            ObjectUtil.isNotEmpty(query.getRequestMethod()),
            SysOperationLog::getRequestMethod,
            query.getRequestMethod())
        .like(ObjectUtil.isNotEmpty(query.getUrl()), SysOperationLog::getUrl, query.getUrl())
        .like(ObjectUtil.isNotEmpty(query.getParam()), SysOperationLog::getParam, query.getParam())
        .eq(
            ObjectUtil.isNotEmpty(query.getStatus()),
            SysOperationLog::getStatus,
            query.getStatus());
    return this.list(queryWrapper);
  }
}
