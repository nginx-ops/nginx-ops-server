package io.github.nginx.ops.server.conf.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

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
public class ConfInfoUpstreamDTO implements Serializable {

  /** 名称 */
  private String name;
  /** 负载策略 */
  private String tactics;
  /** 转发类型(HTTP or UDP) */
  private String proxyType;
  /** 顺序 */
  private Integer order;
  /** 明细 */
  private Set<ConfInfoUpstreamItemDTO> confInfoUpstreamItemList;
  /** 额外参数 */
  private Set<ConfInfoCommDTO> confInfoCommList;
}
