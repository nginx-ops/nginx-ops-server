package io.github.nginx.ops.server.system.aspect;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson2.JSON;
import io.github.nginx.ops.server.system.annotation.OperationLog;
import io.github.nginx.ops.server.system.domain.SysOperationLog;
import io.github.nginx.ops.server.system.service.SysOperationLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lihao3
 * @date 2022/8/21
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class OperationLogAspect {

  private final SysOperationLogService service;

  /** 设置操作日志切入点 记录操作日志 在注解的位置切入代码 */
  @Pointcut("@annotation(io.github.nginx.ops.server.system.annotation.OperationLog)")
  public void operationLogPointcut() {}

  /**
   * 正常请求切面记录
   *
   * @param joinPoint
   * @return
   */
  @AfterReturning(value = "operationLogPointcut()", returning = "result")
  public void doAfterReturning(JoinPoint joinPoint, Object result) {
    this.buildAndSaveData(joinPoint, null, result);
  }

  /**
   * 异常请求切面记录
   *
   * @param joinPoint
   * @param e
   */
  @AfterThrowing(pointcut = "operationLogPointcut()", throwing = "e")
  public void doAfterThrow(JoinPoint joinPoint, Exception e) {
    this.buildAndSaveData(joinPoint, e, null);
  }

  /**
   * 封装并持久化数据
   *
   * @param joinPoint
   * @param e
   * @param result
   */
  private void buildAndSaveData(final JoinPoint joinPoint, final Exception e, Object result) {
    // 获取流
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // 获得注解
    OperationLog controllerLog = this.getAnnotationLog(joinPoint);
    // 解析浏览器
    UserAgent userAgent = UserAgentUtil.parse(request.getHeader(HttpHeaders.USER_AGENT));
    // 封装DTO
    SysOperationLog sysOperationLog =
        SysOperationLog.builder()
            .title(controllerLog.title())
            .method(
                String.format(
                    "%s.%s()",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName()))
            .browser(userAgent.getBrowser() + " " + userAgent.getVersion())
            .os(userAgent.getPlatform() + " " + userAgent.getOsVersion())
            .businessType(controllerLog.businessType().getCode())
            .requestMethod(request.getMethod())
            .url(request.getRequestURL().toString())
            .ip(ServletUtil.getClientIP(request))
            .param(
                controllerLog.isSaveRequestData()
                    ? JSON.toJSONString(this.getRequestParamsByJoinPoint(joinPoint))
                    : null)
            .result(controllerLog.isSaveReturnData() ? JSON.toJSONString(result) : null)
            .status(e == null)
            .errorMsg(e == null ? null : e.getMessage())
            .build();
    // 持久化
    service.save(sysOperationLog);
  }

  /**
   * 获取入参
   *
   * @param joinPoint
   * @return
   */
  private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
    // 参数名
    String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
    // 参数值
    Object[] paramValues = joinPoint.getArgs();
    return this.buildRequestParam(paramNames, paramValues);
  }

  /**
   * 构建请求参数
   *
   * @param paramNames
   * @param paramValues
   * @return
   */
  private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
    Map<String, Object> requestParams = new HashMap<>(paramNames.length);
    for (int i = 0; i < paramNames.length; i++) {
      Object value = paramValues[i];
      // 跳过不能获取参数的类
      if (value instanceof ServletRequest || value instanceof ServletResponse) {
        continue;
      } else if (value instanceof MultipartFile) {
        MultipartFile file = (MultipartFile) value;
        // 获取文件名
        value = file.getOriginalFilename();
      } else if (value instanceof MultipartFile[]) {
        MultipartFile[] files = (MultipartFile[]) value;
        // 获取文件名
        value =
            Arrays.stream(files)
                .map(MultipartFile::getOriginalFilename)
                .collect(Collectors.joining(","));
      }
      requestParams.put(paramNames[i], value);
    }
    return requestParams;
  }

  /** 是否存在注解，如果存在就获取 */
  private OperationLog getAnnotationLog(JoinPoint joinPoint) {
    Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    Method method = methodSignature.getMethod();
    return method.getAnnotation(OperationLog.class);
  }
}
