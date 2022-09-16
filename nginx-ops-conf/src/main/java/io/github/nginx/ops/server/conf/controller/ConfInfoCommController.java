package io.github.nginx.ops.server.conf.controller;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.github.nginx.ops.server.comm.annotation.OperationLog;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.enums.BusinessTypeEnum;
import io.github.nginx.ops.server.conf.domain.ConfInfoComm;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoCommDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoCommQuery;
import io.github.nginx.ops.server.conf.service.ConfInfoCommService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/17
 */
@Api(tags = "nginx通用配置项目接口")
@Slf4j
@Validated
@RestController
@RequestMapping("conf/info/comm")
@RequiredArgsConstructor
public class ConfInfoCommController {

  private final ConfInfoCommService service;

  @PostMapping
  @ApiOperation("新增接口")
  @OperationLog(title = "新增nginx通用配置文件表", businessType = BusinessTypeEnum.UPDATE)
  public R save(
      @RequestBody @Valid @NotEmpty(message = "{confInfoCommDTO.notEmpty}")
          List<ConfInfoCommDTO> dtoList) {
    service.saveBatch(BeanUtil.copyToList(dtoList, ConfInfoComm.class));
    return R.success("新增成功!");
  }

  @DeleteMapping("{ids}")
  @ApiOperation("删除接口")
  @OperationLog(title = "删除nginx通用配置文件表", businessType = BusinessTypeEnum.UPDATE)
  public R delete(@PathVariable List<String> ids) {
    service.removeByIds(ids);
    return R.success("删除成功!");
  }

  @PutMapping("{id}")
  @ApiOperation("修改接口")
  @OperationLog(title = "修改nginx通用配置文件表", businessType = BusinessTypeEnum.UPDATE)
  public R update(@PathVariable String id, @RequestBody ConfInfoCommDTO dto) {
    ConfInfoComm confInfoComm = BeanUtil.copyProperties(dto, ConfInfoComm.class);
    confInfoComm.setId(id);
    service.updateById(confInfoComm);
    return R.success("修改成功!");
  }

  @GetMapping
  @ApiOperation("查询列表接口")
  @OperationLog(title = "查询nginx通用配置文件表", businessType = BusinessTypeEnum.SELECT)
  public R<List<ConfInfoComm>> list(@ModelAttribute ConfInfoCommQuery query) {
    List<ConfInfoComm> confInfoCommList = service.list(query);
    return R.success("查询成功!", confInfoCommList);
  }

  @GetMapping("page")
  @ApiOperation("分页查询列表接口")
  @OperationLog(title = "分页查询nginx通用配置文件表", businessType = BusinessTypeEnum.SELECT)
  public R<ConfInfoComm> pageList(@ModelAttribute ConfInfoCommQuery query) {
    Page<ConfInfoComm> page = PageHelper.startPage(query);
    List<ConfInfoComm> confInfoCommList = service.list(query);
    return R.success("查询成功!", page);
  }
}
