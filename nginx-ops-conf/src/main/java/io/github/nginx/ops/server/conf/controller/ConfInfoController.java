package io.github.nginx.ops.server.conf.controller;

import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.conf.domain.dto.ReloadDTO;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoVO;
import io.github.nginx.ops.server.conf.domain.vo.FileVo;
import io.github.nginx.ops.server.conf.service.ConfInfoService;
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

  @ApiOperation("获取磁盘树")
  @GetMapping("node/list")
  public R<List<FileVo>> nodeList(String pid) {
    return R.success("查询成功", service.nodeList(pid));
  }

  @GetMapping("preview")
  @ApiOperation("预览")
  public R<ConfInfoVO> preview(String type, String id) {
    return R.success("预览成功!", service.preview(type, id));
  }

  @ApiOperation("校验文件")
  @PostMapping("test")
  public R test() {
    String result = service.test();
    return R.success(result);
  }

  @ApiOperation("替换文件")
  @PostMapping("replace")
  public R replace() {
    service.replace();
    return R.success("替换成功!");
  }

  @ApiOperation("重新装配")
  @PostMapping("reload")
  public R reload(@RequestBody ReloadDTO reloadDTO) {
    String result = service.reload(reloadDTO);
    return R.success(result);
  }

  @ApiOperation("停止")
  @PostMapping("stop")
  public R stop() {
    String result = service.stop();
    return R.success(result);
  }

  @ApiOperation("启动")
  @PostMapping("run")
  public R run() {
    String result = service.run();
    return R.success(result);
  }

  @ApiOperation("当前运行状态")
  @PostMapping("status")
  public R status() {
    return R.success("获取成功!", service.status());
  }

  @GetMapping
  @ApiOperation("获取当前生效的配置文件")
  public R<ConfInfoVO> get() {
    return R.success("预览成功!", service.get());
  }
}
