package io.github.nginx.ops.server.system.domain.query;

import io.github.nginx.ops.server.comm.domain.query.BaseQuery;
import io.swagger.annotations.ApiModel;
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
 * @date 2022/9/13 9:28
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("操作日志查询实体类")
public class SysOperationLogQuery extends BaseQuery implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 模块标题 */
  private String title;
  /** 业务类型 */
  private String businessType;
  /** 请求方式 */
  private String requestMethod;
  /** 请求URL */
  private String url;
  /** 请求参数 */
  private String param;
  /** 操作状态 */
  private Boolean status;
}
