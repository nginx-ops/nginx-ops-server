package io.github.nginx.ops.server.admin.controller;

import io.github.nginx.ops.server.admin.domain.dto.LoginDTO;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.system.annotation.OperationLog;
import io.github.nginx.ops.server.system.enums.BusinessTypeEnum;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  @PostMapping("/login")
  @OperationLog(title = "登录", businessType = BusinessTypeEnum.LOGIN)
  public R login(@RequestBody LoginDTO dto) {
    return R.success("登录成功!");
  }
}
