package io.github.nginx.ops.server.comm.constant;

/**
 * 通用异常码定义
 *
 * @author lihao3
 */
public class CommReturnCodeConstant {

  /** 参数异常 */
  public static final String CAPTCHA_HAS_EXPIRED = "C1001";
  /** 请求格式异常 */
  public static final String HTTP_REQUEST_METHOD_NOT_SUPPORTED = "C1002";
  /** 运行异常 */
  public static final String RUNTIME_EXCEPTION = "C1003";
  /** 未知异常 */
  public static final String EXCEPTION = "C1004";
}
