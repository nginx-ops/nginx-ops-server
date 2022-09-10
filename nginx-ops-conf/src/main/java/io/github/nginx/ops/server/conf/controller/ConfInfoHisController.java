package io.github.nginx.ops.server.conf.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.github.nginx.ops.server.comm.annotation.OperationLog;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.enums.BusinessTypeEnum;
import io.github.nginx.ops.server.conf.domain.ConfInfoHis;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoHisQuery;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoVO;
import io.github.nginx.ops.server.conf.service.ConfInfoHisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/21
 */
@Api(tags = "nginx历史配置接口")
@Slf4j
@RestController
@RequestMapping("conf/info/his")
@RequiredArgsConstructor
public class ConfInfoHisController {

  private final ConfInfoHisService service;

  @PutMapping("back/{id}")
  @ApiOperation("回滚配置文件")
  @OperationLog(title = "回滚配置文件", businessType = BusinessTypeEnum.UPDATE)
  public R back(@PathVariable String id) {
    service.back(id);
    return R.success("回滚成功!");
  }

  @GetMapping
  @ApiOperation("查询列表接口")
  @OperationLog(title = "查询nginx历史配置表", businessType = BusinessTypeEnum.SELECT)
  public R<List<ConfInfoHis>> list(@ModelAttribute ConfInfoHisQuery query) {
    List<ConfInfoHis> confInfoCommList = service.list(query);
    return R.success("查询成功!", confInfoCommList);
  }

  @GetMapping("page")
  @ApiOperation("分页查询列表接口")
  @OperationLog(title = "分页查询nginx历史配置表", businessType = BusinessTypeEnum.SELECT)
  public R<ConfInfoHis> pageList(@ModelAttribute ConfInfoHisQuery query) {
    Page<ConfInfoHis> page = PageHelper.startPage(query);
    List<ConfInfoHis> confInfoHisList = service.list(query);
    return R.success("查询成功!", page);
  }

  @GetMapping("one/{id}")
  @ApiOperation("查询历史配置文件详情")
  @OperationLog(title = "查询历史配置文件详情", businessType = BusinessTypeEnum.SELECT)
  public R<ConfInfoVO> pageList(@PathVariable String id) {
    ConfInfoVO confInfoVO = service.getOne(id);
    return R.success("查询成功!", confInfoVO);
  }
}
