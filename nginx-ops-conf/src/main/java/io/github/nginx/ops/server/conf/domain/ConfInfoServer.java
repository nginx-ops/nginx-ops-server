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

/** nginx服务配置表 @TableName conf_info_server */
@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoServer extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 租户ID */
  @TableField(fill = FieldFill.INSERT)
  private String tenantId;
  /** 监听域名 */
  private String serverName;
  /** 监听IP */
  private String ip;
  /** 监听端口 */
  private Integer port;
  /** 服务类型 */
  private String type;
  /** 证书表ID */
  private String certificateId;
  /** 是否开启证书 */
  private Boolean isSsl;
  /** 是否开启http2 */
  private Boolean isHttp2;
  /** 是否开启http跳转https */
  private Boolean httpToHttps;
  /** http跳转https的ip */
  private String httpIp;
  /** http跳转https的端口 */
  private Integer httpPort;
  /** 是否启用 */
  private Boolean isEnable;
  /** 顺序 */
  @TableField("`order`")
  @OrderBy(asc = true)
  private Integer order;
  /** 备注 */
  private String remark;
  /** 逻辑删除 */
  private Boolean deleteFlag;
}
