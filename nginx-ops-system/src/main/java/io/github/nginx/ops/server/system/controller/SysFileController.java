package io.github.nginx.ops.server.system.controller;

import io.github.nginx.ops.server.comm.annotation.OperationLog;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.enums.BusinessTypeEnum;
import io.github.nginx.ops.server.system.domain.SysFile;
import io.github.nginx.ops.server.system.domain.dto.SysFileDTO;
import io.github.nginx.ops.server.system.domain.query.SysFileQuery;
import io.github.nginx.ops.server.system.service.SysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/22
 */
@Api(tags = "文件管理接口")
@Slf4j
@RestController
@RequestMapping("sys/file")
@RequiredArgsConstructor
public class SysFileController {

  private final SysFileService service;

  @PostMapping("upload")
  @ApiOperation("上传文件")
  @OperationLog(title = "上传文件", businessType = BusinessTypeEnum.UPLOAD)
  public R upload(SysFileDTO dto) {
    SysFile sysFile = service.upload(dto);
    return R.success("上传成功!", sysFile);
  }

  @DeleteMapping("delete/{id}")
  @ApiOperation("删除文件")
  @OperationLog(title = "删除文件", businessType = BusinessTypeEnum.DELETE)
  public R delete(@PathVariable String id) {
    service.delete(id);
    return R.success("删除成功!");
  }

  @DeleteMapping("deleteBatch")
  @ApiOperation("批量删除文件")
  @OperationLog(title = "批量删除文件", businessType = BusinessTypeEnum.DELETE)
  public R deleteBatch(@RequestBody List<String> ids) {
    service.deleteBatch(ids);
    return R.success("批量删除成功!");
  }

  @GetMapping("list/{page}/{limit}")
  @ApiOperation("分页查询文件")
  @OperationLog(title = "分页查询文件", businessType = BusinessTypeEnum.SELECT)
  public R pageList(@PathVariable Long page, @PathVariable Long limit, SysFileQuery sysFileQuery) {
    return service.selectPage(page, limit, sysFileQuery);
  }
}
