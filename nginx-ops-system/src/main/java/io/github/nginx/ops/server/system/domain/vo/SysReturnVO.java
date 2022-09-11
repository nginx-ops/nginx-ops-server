package io.github.nginx.ops.server.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 返回语句VO
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
public class SysReturnVO implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 默认返回语句（中文） */
  private String message;
  /** 英语返回语句 */
  private String enMessage;
}
