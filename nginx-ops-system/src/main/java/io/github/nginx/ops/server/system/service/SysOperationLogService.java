package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysOperationLog;
import io.github.nginx.ops.server.system.domain.query.SysOperationLogQuery;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【sys_operation_log(操作日志表)】的数据库操作Service
 * @createDate 2022-08-21 21:06:44
 */
public interface SysOperationLogService extends IService<SysOperationLog> {

  /**
   * 查询操作日志列表
   *
   * @param query
   * @return
   */
  List<SysOperationLog> list(SysOperationLogQuery query);
}
