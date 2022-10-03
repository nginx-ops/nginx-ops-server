package io.github.nginx.ops.server.admin.constant;

/**
 * 鉴权模块异常码常量类
 *
 * @author lihao3
 */
public class AdminReturnCodeConstant {

  /** 验证码过期 */
  public static final String CAPTCHA_HAS_EXPIRED = "A0001";
  /** 登录名或密码错误 */
  public static final String USER_DOES_NOT_EXIST = "A0002";
  /** 账号被停用 */
  public static final String USER_NOT_ENABLE = "A0003";
  /** 验证码错误 */
  public static final String CAPTCHA_ERROR = "A0004";
}
