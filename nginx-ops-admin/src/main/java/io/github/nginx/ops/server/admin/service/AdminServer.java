package io.github.nginx.ops.server.admin.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.github.nginx.ops.server.admin.domain.dto.LoginDTO;
import io.github.nginx.ops.server.admin.domain.vo.CaptchaVO;
import io.github.nginx.ops.server.admin.domain.vo.RouterVo;
import io.github.nginx.ops.server.system.domain.dto.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminServer {

  /**
   * 获取用户信息, 角色信息, 菜单信息等
   *
   * @return
   */
  UserInfo getUserInfo();

  /**
   * 登录
   *
   * @param dto
   * @param request
   * @return
   */
  SaTokenInfo login(LoginDTO dto, HttpServletRequest request);

  /**
   * 获取路由列表
   *
   * @return @return {@link List }<{@link RouterVo }>
   * @author wgy
   * @data 2022/10/05
   */
  List<RouterVo> getRouters();

  /** 登出 */
  void logout();

  /**
   * 获取验证码
   *
   * @return
   */
  CaptchaVO captcha();
}
