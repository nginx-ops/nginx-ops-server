package io.github.nginx.ops.server.conf.domain.query;

import io.github.nginx.ops.server.comm.domain.query.BaseQuery;
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
 * @date 2022/8/22
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoCertificateQuery extends BaseQuery implements Serializable {

  /** 域名 */
  private String domain;
}
