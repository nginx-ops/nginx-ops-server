package io.github.nginx.ops.server.conf.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lihao3
 * @date 2022/8/22
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoCertificateDTO implements Serializable {

  /** 域名 */
  private String domain;
  /** ssl证书的pem文件路径 */
  private String pem;
  /** ssl证书的key文件路径 */
  private String key;
}
