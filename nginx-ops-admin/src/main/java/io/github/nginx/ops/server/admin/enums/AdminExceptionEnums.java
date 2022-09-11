package io.github.nginx.ops.server.admin.enums;

import io.github.nginx.ops.server.comm.domain.vo.ReturnService;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 鉴权异常国际化
 *
 * @author lihao3
 */
@Getter
@AllArgsConstructor
public enum AdminExceptionEnums implements ReturnService {
  A0001("A0001", "验证码已过期!", ""),
  A0002("A0002", "登录名或密码错误!", ""),
  ;

  private final String code;
  private final String message;
  private final String enMessage;

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public String getEnMessage() {
    return this.enMessage;
  }
}
