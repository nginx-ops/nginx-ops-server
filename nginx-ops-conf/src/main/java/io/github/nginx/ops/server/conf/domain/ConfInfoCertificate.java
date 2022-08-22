package io.github.nginx.ops.server.conf.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/** 证书表 @TableName conf_info_certificate */
@Setter
@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoCertificate extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 租户ID */
  @TableField(fill = FieldFill.INSERT)
  private String tenantId;
  /** 域名 */
  private String domain;
  /** ssl证书的pem文件路径 */
  private String pem;
  /** ssl证书的key文件路径 */
  private String key;
}
