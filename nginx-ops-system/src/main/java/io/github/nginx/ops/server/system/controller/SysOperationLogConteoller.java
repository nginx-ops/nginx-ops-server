package io.github.nginx.ops.server.system.controller;

import com.github.pagehelper.PageHelper;
import io.github.nginx.ops.server.comm.annotation.OperationLog;
import io.github.nginx.ops.server.comm.domain.query.BaseQuery;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.enums.BusinessTypeEnum;
import io.github.nginx.ops.server.system.domain.SysOperationLog;
import io.github.nginx.ops.server.system.service.SysOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: nginx-ops-server @BelongsPackage:
 * io.github.nginx.ops.server.system.controller @Author: wgy @CreateTime: 2022-09-11
 * 19:06:37 @Description:
 */
@Api(tags = "日志管理接口")
@Slf4j
@RestController
@RequestMapping("sys/log")
@RequiredArgsConstructor
public class SysOperationLogConteoller {

  private final SysOperationLogService sysOperationLogService;

  @GetMapping("loginLog")
  @ApiOperation("登录日志")
  @OperationLog(title = "登录日志", businessType = BusinessTypeEnum.SELECT)
  public R loginLogList(@ModelAttribute BaseQuery baseQuery) {
    PageHelper.startPage(baseQuery);
    List<SysOperationLog> sysOperationLogs = sysOperationLogService.logPageList(baseQuery, true);
    return R.success("登陆日志分页查询成功", sysOperationLogs);
  }

  @GetMapping("operationLog")
  @ApiOperation("操作日志")
  @OperationLog(title = "操作日志", businessType = BusinessTypeEnum.SELECT)
  public R operationLogList(@ModelAttribute BaseQuery baseQuery) {
    PageHelper.startPage(baseQuery);
    List<SysOperationLog> sysOperationLogs = sysOperationLogService.logPageList(baseQuery, false);
    return R.success("操作日志分页查询成功", sysOperationLogs);
  }
}
