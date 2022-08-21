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
  INDEX("index"),
  HTTP("http"),
  STREAM("stream"),
  SERVER("server"),
  UPSTREAM("upstream"),
  ;

  private final String code;
}
