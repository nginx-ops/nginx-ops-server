package io.github.nginx.ops.server.system.annotation;

import io.github.nginx.ops.server.system.enums.BusinessTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解
 *
 * @author 24709
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

  /** 模块 */
  String title();
  /** 功能 */
  BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;
  /** 是否保存请求的参数 */
  boolean isSaveRequestData() default true;
  /** 是否保存返回的参数 */
  boolean isSaveReturnData() default true;
}
