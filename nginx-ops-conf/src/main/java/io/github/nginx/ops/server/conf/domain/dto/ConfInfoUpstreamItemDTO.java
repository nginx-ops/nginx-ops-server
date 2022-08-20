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
 * @date 2022/8/20
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoUpstreamItemDTO implements Serializable {

  /** ip */
  private String ip;
  /** 端口 */
  private Integer port;
  /** 权重 */
  private Integer weight;
  /** 失败等待时间(s) */
  private Integer failTimeout;
  /** 最大失败次数 */
  private Integer maxFails;
  /** 最大连接数 */
  private Integer maxConns;
  /** 状态策略 */
  private String status;
}
