package io.github.nginx.ops.server.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.github.nginx.ops.server.comm.annotation.OperationLog;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.enums.BusinessTypeEnum;
import io.github.nginx.ops.server.system.domain.SysFile;
import io.github.nginx.ops.server.system.domain.dto.SysFileDTO;
import io.github.nginx.ops.server.system.domain.query.SysFileQuery;
import io.github.nginx.ops.server.system.service.SysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.HashSet;
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
    service.upload(dto);
    return R.success("上传成功!");
  }

  @GetMapping("download")
  @ApiOperation("下载文件")
  @OperationLog(title = "下载文件", businessType = BusinessTypeEnum.DOWNLOAD)
  public void download(
      @ApiParam(value = "文件ID", required = true) @NotEmpty(message = "文件ID不可为空") String id,
      @ApiParam(value = "是否预览") Boolean preview,
      HttpServletResponse response) {
    service.download(id, preview, response);
  }

  @GetMapping("download/zip")
  @ApiOperation("批量下载至压缩包")
  @OperationLog(title = "批量下载至压缩包", businessType = BusinessTypeEnum.DOWNLOAD)
  public void downloadZip(
      @ApiParam(value = "文件ID(多个采用逗号分割)", required = true) @NotEmpty(message = "文件ID不可为空")
          String ids,
      HttpServletResponse response) {
    service.downloadZip(new HashSet<>(Arrays.asList(ids.split(","))), response);
  }

  @DeleteMapping("{id}")
  @ApiOperation("删除文件")
  @OperationLog(title = "删除文件", businessType = BusinessTypeEnum.DELETE)
  public R delete(@PathVariable String id) {
    service.delete(id);
    return R.success("删除成功!");
  }

  @GetMapping("page")
  @ApiOperation("分页查询文件")
  @OperationLog(title = "分页查询文件", businessType = BusinessTypeEnum.SELECT)
  public R pageList(@ModelAttribute SysFileQuery query) {
    Page<SysFile> page = PageHelper.startPage(query);
    List<SysFile> sysFileList = service.list(query);
    return R.success("查询成功!", page);
  }
}
