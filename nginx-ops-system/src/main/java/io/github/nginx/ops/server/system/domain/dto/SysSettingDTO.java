package io.github.nginx.ops.server.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lihao3
 * @date 2022/8/24 10:06
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SysSettingDTO implements Serializable {

  /** 唯一ID */
  private String id;
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
  /** 是否按照域名分割文件 */
  private Boolean isSplit;
}
