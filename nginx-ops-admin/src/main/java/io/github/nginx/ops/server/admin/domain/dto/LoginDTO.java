package io.github.nginx.ops.server.admin.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author lihao3
 * @date 2022/8/31 19:05
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {

  /** 用户名 */
  @ApiModelProperty(value = "用户名", required = true)
  @NotEmpty(message = "用户名不能为空")
  private String loginName;
  /** 密码 */
  @ApiModelProperty(value = "密码", required = true)
  @NotEmpty(message = "密码不能为空")
  private String password;
  /** 验证码唯一ID */
  @ApiModelProperty(value = "验证码唯一ID", required = true)
  @NotEmpty(message = "验证码不能为空")
  private String verKey;
  /** 验证码内容 */
  @ApiModelProperty(value = "验证码内容", required = true)
  @NotEmpty(message = "验证码不能为空")
  private String verCode;
}
