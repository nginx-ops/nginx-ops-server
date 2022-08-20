package io.github.nginx.ops.server.conf.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lihao3
 * @date 2022/8/18
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileVo implements Serializable {

  /** 当前ID */
  private String id;
  /** 父ID */
  private String pid;
  /** 名称 */
  private String name;
  /** 是否为父级 */
  private Boolean isParent;
}
