package io.github.nginx.ops.server.system.domain;

import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 国际化返回表
 *
 * @tableName sys_return
 * @author lihao3
 */
@Setter
@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SysReturn extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 唯一编码 */
  private String code;
  /** 默认返回语句（中文） */
  private String message;
  /** 英语返回语句 */
  private String enMessage;
}
