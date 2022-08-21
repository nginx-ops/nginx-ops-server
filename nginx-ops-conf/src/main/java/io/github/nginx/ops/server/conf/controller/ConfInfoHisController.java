package io.github.nginx.ops.server.conf.controller;

import io.github.nginx.ops.server.conf.service.ConfInfoHisService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
