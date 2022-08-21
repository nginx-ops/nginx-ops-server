package io.github.nginx.ops.server.conf.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/20
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoServerDTO implements Serializable {

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
  private Integer order;
  /** 备注 */
  private String remark;
  /** 其他参数 */
  private List<ConfInfoCommDTO> confInfoCommList;
  /** 明细 */
  private List<ConfInfoServerItemDTO> confInfoServerItemList;
}
