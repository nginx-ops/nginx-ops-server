package io.github.nginx.ops.server.conf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lihao3
 * @date 2022/8/21
 */
@Getter
@AllArgsConstructor
public enum NginxConfTypeEnum {

  /** 入门 */
  MAIN("main"),
  /** HTTP属性 */
  HTTP("http"),
  /** stream */
  STREAM("stream"),
  /** server */
  SERVER("server"),
  /** upstream */
  UPSTREAM("upstream"),
  ;

  private final String code;
}
