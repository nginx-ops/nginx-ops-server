package io.github.nginx.ops.server.system.domain.query;

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
 * @date 2022/8/31 10:07
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SysSettingQuery implements Serializable {

  /** 名称 */
  private String name;
  /** 地址 */
  private String url;
}
