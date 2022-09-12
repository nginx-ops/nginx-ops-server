package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.system.domain.SysOperationLog;
import io.github.nginx.ops.server.system.mapper.SysOperationLogMapper;
import io.github.nginx.ops.server.system.service.SysOperationLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【sys_operation_log(操作日志表)】的数据库操作Service实现
 * @createDate 2022-08-21 21:06:44
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog>
    implements SysOperationLogService {

  @Override
  public R loginLogList(Long page, Long limit, String type) {
    QueryWrapper<SysOperationLog> queryWrapper = new QueryWrapper<>();

    if (!StringUtils.isEmpty(type)) {
      queryWrapper.eq("business_type", type);
    }

    // 分页查询
    Page<SysOperationLog> operationLogPage =
        baseMapper.selectPage(new Page<>(page, limit), queryWrapper);

    return R.builder()
        .count(operationLogPage.getTotal())
        .data(operationLogPage.getRecords())
        .message("登录日志分页查询成功")
        .time(System.currentTimeMillis())
        .code(R.SUCCESS)
        .build();
  }

  @Override
  public R operationLogList(Long page, Long limit, String type) {
    QueryWrapper<SysOperationLog> queryWrapper = new QueryWrapper<>();

    if (!StringUtils.isEmpty(type)) {
      queryWrapper.ne("business_type", type);
    }

    // 分页查询
    Page<SysOperationLog> operationLogPage =
        baseMapper.selectPage(new Page<>(page, limit), queryWrapper);

    return R.builder()
        .count(operationLogPage.getTotal())
        .data(operationLogPage.getRecords())
        .message("操作日志分页查询成功")
        .time(System.currentTimeMillis())
        .code(R.SUCCESS)
        .build();
  }
}
