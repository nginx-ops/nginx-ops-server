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

/** nginx 负载均衡明细配置表 @TableName conf_info_upstream_item */
@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoUpstreamItem extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 租户ID */
  @TableField(fill = FieldFill.INSERT)
  private String tenantId;
  /** 负载均衡头表ID */
  private String upstreamId;
  /** ip */
  private String ip;
  /** 端口 */
  private Integer port;
  /** 权重 */
  private Integer weight;
  /** 失败等待时间(s) */
  private Integer failTimeout;
  /** 最大失败次数 */
  private Integer maxFails;
  /** 最大连接数 */
  private Integer maxConns;
  /** 状态策略 */
  private String status;
  /** 逻辑删除 */
  private Boolean deleteFlag;
}
