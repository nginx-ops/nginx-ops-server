package io.github.nginx.ops.server.conf.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/** nginx明细配置表 @TableName conf_info_item */
@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoItem extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 租户ID */
  @TableField(fill = FieldFill.INSERT)
  private String tenantId;
  /** 文件ID */
  private String confId;
  /** 文件名称 */
  private String name;
  /** 文件路径 */
  private String path;
  /** 配置文件 */
  private String content;
}
