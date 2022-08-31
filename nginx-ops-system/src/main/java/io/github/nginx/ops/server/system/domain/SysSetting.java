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

/** 系统设置 @TableName sys_setting */
@Setter
@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SysSetting extends BaseEntity implements Serializable {

  /** 分组ID */
  private String groupId;
  /** 名称 */
  private String name;
  /** 地址 */
  private String url;
  /** 顺序 */
  private Integer order;
  /** nginx执行文件 */
  private String nginxExe;
  /** 是否为本地环境 */
  private Boolean isLocalhost;
  /** nginx启动命令 */
  private String nginxStartCmd;
  /** nginx重启命令 */
  private String nginxReloadCmd;
  /** nginx停止命令 */
  private String nginxStopCmd;
  /** nginx配置文件夹路径 */
  private String nginxConfPath;
  /** nginx执行文件 */
  private String nginxPath;
  /** 备注 */
  private String remark;
  /** 逻辑删除 */
  private Boolean deleteFlag;
  /** 是否按照域名分割文件 */
  private Boolean isSplit;

  private static final long serialVersionUID = 1L;
}
