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

/** nginx 负载均衡头表配置表 @TableName conf_info_upstream */
@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoUpstream extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 租户ID */
  @TableField(fill = FieldFill.INSERT)
  private String tenantId;
  /** 名称 */
  private String name;
  /** 负载策略 */
  private String tactics;
  /** 转发类型(HTTP or UDP) */
  private String proxyType;
  /** 顺序 */
  @OrderBy(asc = true)
  @TableField("`order`")
  private Integer order;
  /** 备注 */
  private String remark;
  /** 逻辑删除 */
  private Boolean deleteFlag;
}
