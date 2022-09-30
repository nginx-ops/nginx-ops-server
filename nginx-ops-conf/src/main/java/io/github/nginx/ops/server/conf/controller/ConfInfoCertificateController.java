package io.github.nginx.ops.server.conf.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.github.nginx.ops.server.comm.annotation.OperationLog;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.enums.BusinessTypeEnum;
import io.github.nginx.ops.server.conf.domain.ConfInfoCertificate;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoCertificateDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoCertificateQuery;
import io.github.nginx.ops.server.conf.service.ConfInfoCertificateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/22
 */
@Api(tags = "nginx证书相关接口")
@Slf4j
@RestController
@RequestMapping("conf/info/certificate")
@RequiredArgsConstructor
public class ConfInfoCertificateController {

  private final ConfInfoCertificateService service;

  @PostMapping
  @ApiOperation("新增接口")
  @OperationLog(title = "新增证书信息", businessType = BusinessTypeEnum.INSERT)
  public R save(@RequestBody ConfInfoCertificateDTO dto) {
    service.save(dto);
    return R.success("新增成功!");
  }

  @DeleteMapping("{id}")
  @ApiOperation("删除接口")
  @OperationLog(title = "删除证书信息", businessType = BusinessTypeEnum.DELETE)
  public R delete(@PathVariable String id) {
    service.delete(id);
    return R.success("删除成功!");
  }

  @PutMapping("{id}")
  @ApiOperation("修改接口")
  @OperationLog(title = "修改证书信息", businessType = BusinessTypeEnum.UPDATE)
  public R update(@PathVariable String id, @RequestBody ConfInfoCertificateDTO dto) {
    service.updateById(id, dto);
    return R.success("修改成功!");
  }

  @GetMapping
  @ApiOperation("查询列表接口")
  @OperationLog(title = "查询证书信息列表", businessType = BusinessTypeEnum.SELECT)
  public R<List<ConfInfoCertificate>> list(@ModelAttribute ConfInfoCertificateQuery query) {
    List<ConfInfoCertificate> list = service.list(query);
    return R.success("查询成功!", list);
  }

  @GetMapping("page")
  @ApiOperation("分页查询列表接口")
  @OperationLog(title = "分页查询证书信息列表", businessType = BusinessTypeEnum.SELECT)
  public R<ConfInfoCertificate> pageList(@ModelAttribute ConfInfoCertificateQuery query) {
    Page<ConfInfoCertificate> page = PageHelper.startPage(query);
    List<ConfInfoCertificate> list = service.list(query);
    return R.success("查询成功!", page);
  }

  @GetMapping("{id}")
  @ApiOperation("查询单条信息")
  @OperationLog(title = "查询单条证书信息", businessType = BusinessTypeEnum.SELECT)
  public R<ConfInfoCertificateDTO> getOne(@PathVariable String id) {
    ConfInfoCertificateDTO confInfoCertificateDTO = service.getOne(id);
    return R.success("查询成功!", confInfoCertificateDTO);
  }
}
