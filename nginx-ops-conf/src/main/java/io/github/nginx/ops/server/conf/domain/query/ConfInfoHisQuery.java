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
 * @date 2022/8/23 10:25
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoHisQuery extends BaseQuery implements Serializable {

  /** 开始时间 */
  private String startTime;
  /** 结束时间 */
  private String endTime;
}
