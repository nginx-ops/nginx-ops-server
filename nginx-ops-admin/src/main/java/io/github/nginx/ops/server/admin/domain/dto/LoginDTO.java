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
  @NotEmpty(message = "{loginDTO.loginName.notEmpty}")
  private String loginName;
  /** 密码 */
  @ApiModelProperty(value = "密码", required = true)
  @NotEmpty(message = "{loginDTO.password.notEmpty}")
  private String password;
  /** 验证码唯一ID */
  @ApiModelProperty(value = "验证码唯一ID", required = true)
  @NotEmpty(message = "{loginDTO.verId.notEmpty}")
  private String verId;
  /** 验证码内容 */
  @ApiModelProperty(value = "验证码内容", required = true)
  @NotEmpty(message = "{loginDTO.verCode.notEmpty}")
  private String verCode;
  /** 登录设备 */
  private String device;
  /** 是否记住我 */
  private Boolean remember = false;
}
