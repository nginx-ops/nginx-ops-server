package io.github.nginx.ops.server.comm.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author lihao3
 * @since 2022/4/25
 */
@Getter
public class BusinessException extends RuntimeException {

  /** 异常编号 */
  private final String code;
  /** 异常参数 */
  private final Object[] params;

  public BusinessException(String code) {
    this(code, null);
  }

  public BusinessException(String code, Object[] params) {
    super(code);
    this.code = code;
    this.params = params;
  }
}
