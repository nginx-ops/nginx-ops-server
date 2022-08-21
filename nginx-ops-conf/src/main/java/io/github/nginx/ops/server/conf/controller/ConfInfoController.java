package io.github.nginx.ops.server.conf.controller;

import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.conf.domain.dto.ReloadDTO;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoVO;
import io.github.nginx.ops.server.conf.domain.vo.FileVo;
import io.github.nginx.ops.server.conf.service.ConfInfoService;
import io.github.nginx.ops.server.system.annotation.OperationLog;
import io.github.nginx.ops.server.system.enums.BusinessTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/20
 */
@Api(tags = "nginx启用配置接口")
@Slf4j
@RestController
@RequestMapping("conf/info")
@RequiredArgsConstructor
public class ConfInfoController {

  private final ConfInfoService service;

  @GetMapping("node/list")
  @ApiOperation("获取磁盘树")
  @OperationLog(title = "获取磁盘树", businessType = BusinessTypeEnum.SELECT)
  public R<List<FileVo>> nodeList(String pid) {
    return R.success("查询成功", service.nodeList(pid));
  }

  @GetMapping("preview")
  @ApiOperation("预览")
  @OperationLog(title = "预览nginx配置文件", businessType = BusinessTypeEnum.SELECT)
  public R<ConfInfoVO> preview(String type, String id) {
    return R.success("预览成功!", service.preview(type, id));
  }

  @PostMapping("test")
  @ApiOperation("校验文件")
  @OperationLog(title = "预览nginx配置文件", businessType = BusinessTypeEnum.OTHER)
  public R test() {
    String result = service.test();
    return R.success(result);
  }

  @PostMapping("replace")
  @ApiOperation("替换文件")
  @OperationLog(title = "替换nginx配置文件", businessType = BusinessTypeEnum.OTHER)
  public R replace() {
    service.replace();
    return R.success("替换成功!");
  }

  @ApiOperation("重新装配")
  @PostMapping("reload")
  @OperationLog(title = "重新装配nginx配置文件", businessType = BusinessTypeEnum.OTHER)
  public R reload(@RequestBody ReloadDTO reloadDTO) {
    String result = service.reload(reloadDTO);
    return R.success(result);
  }

  @ApiOperation("停止")
  @PostMapping("stop")
  @OperationLog(title = "停止nginx", businessType = BusinessTypeEnum.OTHER)
  public R stop() {
    String result = service.stop();
    return R.success(result);
  }

  @ApiOperation("启动")
  @PostMapping("run")
  @OperationLog(title = "启动nginx配置文件", businessType = BusinessTypeEnum.OTHER)
  public R run() {
    String result = service.run();
    return R.success(result);
  }

  @ApiOperation("当前运行状态")
  @PostMapping("status")
  @OperationLog(title = "获取当前nginx运行状态", businessType = BusinessTypeEnum.OTHER)
  public R status() {
    return R.success("获取成功!", service.status());
  }

  @GetMapping
  @ApiOperation("获取当前生效的配置文件")
  @OperationLog(title = "获取当前生效的配置文件", businessType = BusinessTypeEnum.OTHER)
  public R<ConfInfoVO> get() {
    return R.success("预览成功!", service.get());
  }
}
