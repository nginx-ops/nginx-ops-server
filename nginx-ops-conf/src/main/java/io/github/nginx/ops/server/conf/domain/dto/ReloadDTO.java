package io.github.nginx.ops.server.conf.domain.dto;

import io.github.nginx.ops.server.conf.domain.vo.ConfInfoVO;
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
 * @date 2022/8/21
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReloadDTO implements Serializable {

  /** 新配置文件 */
  private ConfInfoVO newConfInfo;
  /** 旧配置文件 */
  private ConfInfoVO oldConfInfo;
}
