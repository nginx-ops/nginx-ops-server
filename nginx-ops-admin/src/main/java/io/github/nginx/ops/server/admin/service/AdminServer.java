package io.github.nginx.ops.server.admin.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.github.nginx.ops.server.admin.domain.dto.LoginDTO;
import io.github.nginx.ops.server.admin.domain.vo.CaptchaVO;

import javax.servlet.http.HttpServletRequest;

public interface AdminServer {

  /**
   * 登录
   *
   * @param dto
   * @param request
   * @return
   */
  SaTokenInfo login(LoginDTO dto, HttpServletRequest request);

  /** 登出 */
  void logout();

  /**
   * 获取验证码
   *
   * @return
   */
  CaptchaVO captcha();
}
