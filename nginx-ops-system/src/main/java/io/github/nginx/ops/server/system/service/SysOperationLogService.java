package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.system.domain.SysOperationLog;

/**
 * @author 24709
 * @description 针对表【sys_operation_log(操作日志表)】的数据库操作Service
 * @createDate 2022-08-21 21:06:44
 */
public interface SysOperationLogService extends IService<SysOperationLog> {

  /**
   * @description: 登陆日志分页查询
   * @author: wgy
   * @date: 2022/9/11 19:16
   * @param: page
   * @param: limit
   * @return: io.github.nginx.ops.server.comm.domain.vo.R
   */
  R loginLogList(Long page, Long limit, String type);

  /**
   * @description: 操作日志分页查询
   * @author: wgy
   * @date: 2022/9/11 19:42
   * @param: page
   * @param: limit
   * @param: type
   * @return: io.github.nginx.ops.server.comm.domain.vo.R
   */
  R operationLogList(Long page, Long limit, String type);
}
