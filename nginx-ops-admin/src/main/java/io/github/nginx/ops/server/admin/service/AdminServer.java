package io.github.nginx.ops.server.admin.service;

import io.github.nginx.ops.server.admin.domain.dto.LoginDTO;
import io.github.nginx.ops.server.admin.domain.vo.CaptchaVO;

public interface AdminServer {

  /**
   * 登录
   *
   * @param dto
   * @return
   */
  String login(LoginDTO dto);

  /** 登出 */
  void logout();

  /**
   * 获取验证码
   *
   * @return
   */
  CaptchaVO captcha();
}
