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

/** nginx服务明细配置表 @TableName conf_info_server_item */
@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoServerItem extends BaseEntity implements Serializable {

  @TableField(exist = false)
  private static final long serialVersionUID = 1L;
  /** 租户ID */
  @TableField(fill = FieldFill.INSERT)
  private String tenantId;
  /** 服务头表ID */
  private String serverId;
  /** 顺序 */
  @TableField("`order`")
  @OrderBy(asc = true)
  private Integer order;
  /** 监控路径 */
  private String path;
  /** 监控类型 */
  private String type;
  /** 监控地址 */
  private String value;
  /** 是否携带HOST参数 */
  private Boolean isHeader;
  /** 是否开启websocket */
  private Boolean isWebsocket;
  /** 路由模式 */
  private String rootType;
  /** 路由路径 */
  private String rootPath;
  /** 路由默认页 */
  private String rootIndex;
  /** 备注 */
  private String remark;
  /** 逻辑删除 */
  private Boolean deleteFlag;
}
