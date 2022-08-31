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

/** 系统设置-字典表 @TableName sys_dict */
@Setter
@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SysDict extends BaseEntity implements Serializable {

  /** 字典类型 */
  private String type;
  /** 字典编码 */
  private String code;
  /** 字典内容 */
  private String value;
  /** 备注 */
  private String remark;
  /** 逻辑删除 */
  private Boolean deleteFlag;

  private static final long serialVersionUID = 1L;
}
