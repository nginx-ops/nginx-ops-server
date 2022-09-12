package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.comm.domain.query.BaseQuery;
import io.github.nginx.ops.server.system.domain.SysOperationLog;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【sys_operation_log(操作日志表)】的数据库操作Service
 * @createDate 2022-08-21 21:06:44
 */
public interface SysOperationLogService extends IService<SysOperationLog> {

  /**
   * @description: 日志分页查询
   * @author: wgy
   * @date: 2022/9/12 22:45
   * @param: baseQuery
   * @param: flag true 登陆日志 false 操作日志
   * @return: io.github.nginx.ops.server.comm.domain.vo.R
   */
  List<SysOperationLog> logPageList(BaseQuery baseQuery, boolean flag);
}
