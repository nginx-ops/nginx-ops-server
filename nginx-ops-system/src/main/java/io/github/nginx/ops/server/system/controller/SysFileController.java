package io.github.nginx.ops.server.system.controller;

import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.system.annotation.OperationLog;
import io.github.nginx.ops.server.system.domain.SysFile;
import io.github.nginx.ops.server.system.domain.dto.SysFileDTO;
import io.github.nginx.ops.server.system.enums.BusinessTypeEnum;
import io.github.nginx.ops.server.system.service.SysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
