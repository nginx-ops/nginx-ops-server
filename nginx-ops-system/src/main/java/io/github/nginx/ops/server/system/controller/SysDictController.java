package io.github.nginx.ops.server.system.controller;

import io.github.nginx.ops.server.comm.annotation.OperationLog;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.enums.BusinessTypeEnum;
import io.github.nginx.ops.server.system.domain.SysDict;
import io.github.nginx.ops.server.system.domain.query.SysDictQuery;
import io.github.nginx.ops.server.system.domain.vo.SysDictCacheVO;
import io.github.nginx.ops.server.system.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 字典表相关接口
 *
 * @author lihao3
 */
@Api(tags = "字典表接口")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class SysDictController {

  private final SysDictService service;

  @GetMapping
  @ApiOperation("查询列表")
  @OperationLog(title = "查询数据字典列表", businessType = BusinessTypeEnum.SELECT)
  public R<List<SysDict>> list(@ModelAttribute SysDictQuery query) {
    List<SysDict> sysDictList = service.list(query);
    return R.success("查询成功", sysDictList);
  }

  @GetMapping("cache/all")
  @ApiOperation("获取缓存字典")
  @OperationLog(title = "获取缓存字典", businessType = BusinessTypeEnum.SELECT)
  public R<Map<String,List<SysDict>>> getCacheMap() {
    Map<String,List<SysDict>> map=  service.getCacheMap();
    return R.success("查询成功", map);
  }
}
