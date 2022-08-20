package io.github.nginx.ops.server.conf.controller;

import cn.hutool.core.util.ObjectUtil;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.conf.domain.vo.FileVo;
import io.github.nginx.ops.server.conf.service.ConfInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
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

  @ApiOperation("生成配置")
  @GetMapping("generate")
  public R generate() {
    return R.success("生成成功!", service.generate(false));
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
  public R reload() {
    String result = service.reload();
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
    String result = service.status();
    return R.success(result);
  }

  @ApiOperation("获取磁盘树")
  @GetMapping("node/list")
  public R<List<FileVo>> nodeList(String pid) {
    File[] fileList = null;
    if (ObjectUtil.isEmpty(pid)) {
      fileList = File.listRoots();
    } else {
      fileList = new File(pid).listFiles();
    }
    List<FileVo> fileVoList = new ArrayList<>();
    for (File file : fileList) {
      fileVoList.add(
          FileVo.builder()
              .id(file.getPath())
              .pid(file.getParent())
              .isParent(file.isDirectory())
              .name(file.getName())
              .build());
    }
    return R.success("查询成功", fileVoList);
  }
}
