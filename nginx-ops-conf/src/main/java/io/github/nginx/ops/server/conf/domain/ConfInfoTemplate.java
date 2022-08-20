package io.github.nginx.ops.server.conf.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/** nginx配置模板表 @TableName conf_info_template */
@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoTemplate extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 租户ID */
  @TableField(fill = FieldFill.INSERT)
  private String tenantId;
  /** 类型 */
  private String type;
  /** 名称 */
  private String name;
  /** 备注 */
  private String remark;
  /** 排序 */
  @TableField("`order`")
  @OrderBy(asc = true)
  private Integer order;
  /** 逻辑删除 */
  private Boolean deleteFlag;
}
