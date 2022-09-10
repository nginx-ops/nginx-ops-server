package io.github.nginx.ops.server.admin.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import io.github.nginx.ops.server.admin.domain.dto.LoginDTO;
import io.github.nginx.ops.server.admin.domain.vo.CaptchaVO;
import io.github.nginx.ops.server.admin.service.AdminServer;
import io.github.nginx.ops.server.comm.annotation.OperationLog;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.enums.BusinessTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihao3
 * @date 2022/8/31 19:02
 */
@Api(tags = "用户鉴权接口")
@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController {

  private final AdminServer server;
  private final Snowflake SNOWFLAKE = IdUtil.getSnowflake();

  @PostMapping("login")
  @ApiOperation("登录接口")
  @OperationLog(title = "登录", businessType = BusinessTypeEnum.LOGIN)
  public R login(@RequestBody LoginDTO dto) {
    String token = server.login(dto);
    return R.success("登录成功!", token);
  }

  @PostMapping("logout")
  @ApiOperation("登出接口")
  @OperationLog(title = "登出", businessType = BusinessTypeEnum.LOGIN)
  public R logout() {
    server.logout();
    return R.success("登出成功!");
  }

  @GetMapping("captcha")
  @ApiOperation("获取验证码")
  @OperationLog(title = "获取验证码", businessType = BusinessTypeEnum.LOGIN)
  public R captcha() {
    CaptchaVO captchaVO = server.captcha();
    return R.success("获取验证码成功!", captchaVO);
  }
}
