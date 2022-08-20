package io.github.nginx.ops.server.conf.domain.query;

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
 * @date 2022/8/20
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoServerQuery implements Serializable {

  /** 监听域名 */
  private String serverName;
  /** 监听IP */
  private String ip;
  /** 监听端口 */
  private Integer port;
  /** 服务类型 */
  private String type;
  /** 证书表ID */
  private String certificateId;
}
