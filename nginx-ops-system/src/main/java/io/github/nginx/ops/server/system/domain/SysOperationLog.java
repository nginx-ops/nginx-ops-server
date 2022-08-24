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

/**
 * 操作日志表 @TableName sys_operation_log
 *
 * @author 24709
 */
@Setter
@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SysOperationLog extends BaseEntity implements Serializable {

  /** 模块标题 */
  private String title;
  /** 业务类型 */
  private String businessType;
  /** 方法名称 */
  private String method;
  /** 请求方式 */
  private String requestMethod;
  /** 请求URL */
  private String url;
  /** 请求URL */
  private String ip;
  /** 浏览器类型 */
  private String browser;
  /** 操作系统 */
  private String os;
  /** 请求参数 */
  private String param;
  /** 返回参数 */
  private String result;
  /** 操作状态 */
  private Boolean status;
  /** 错误消息 */
  private String errorMsg;

  private static final long serialVersionUID = 1L;
}
