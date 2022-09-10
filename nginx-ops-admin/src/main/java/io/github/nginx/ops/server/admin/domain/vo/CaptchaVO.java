package io.github.nginx.ops.server.admin.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 验证码
 *
 * @author lihao3
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaVO implements Serializable {

  /** 唯一ID */
  private String id;
  /** base64图片 */
  private String image;
}
