package io.github.nginx.ops.server.comm.exception;

import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.domain.vo.ReturnService;
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
  /** 对messageCode 异常信息进行补充说明 */
  private final String message;

  /**
   * 构造函数 默认失败code
   *
   * @param msg
   */
  public BusinessException(String msg) {
    this(R.ERROR, msg);
  }

  /**
   * 有参构造
   *
   * @param messageCode
   * @param message
   */
  public BusinessException(String messageCode, String message) {
    super(message);
    this.code = messageCode;
    this.message = message;
  }

  public BusinessException(ReturnService returnService) {
    super(returnService.getMessage());
    this.code = returnService.getCode();
    this.message = returnService.getMessage();
  }
}
