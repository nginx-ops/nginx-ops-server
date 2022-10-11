package io.github.nginx.ops.server.system.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wgy @CreateTime: 2022-10-05 19:31:13 @Description:
 */
@Api(tags = "菜单管理接口")
@Slf4j
@RestController
@RequestMapping("sys/menu")
@RequiredArgsConstructor
public class SysMenuController {}
