package io.github.nginx.ops.server.admin.handler;

import cn.dev33.satoken.exception.NotLoginException;
import io.github.nginx.ops.server.admin.constant.AdminReturnCodeConstant;
import io.github.nginx.ops.server.comm.constant.CommReturnCodeConstant;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.system.service.SysReturnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 *
 * @author lihao3
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final SysReturnService sysReturnService;

  /** 业务异常 */
  @ExceptionHandler(BusinessException.class)
  public R handleBusinessException(BusinessException e, HttpServletRequest request) {
    log.warn("业务异常, 异常信息为:{}", e.getMessage(), e);
    return R.error(e.getCode(), sysReturnService.getMessage(e.getCode()));
  }

  /** 业务异常 */
  @ExceptionHandler(NotLoginException.class)
  public R handlerNotLoginException(NotLoginException e, HttpServletRequest request) {
    log.warn("鉴权异常, 异常信息为:{}", e.getMessage(), e);
    if (e.getType().equals(NotLoginException.INVALID_TOKEN)) {
      return R.error(
          AdminReturnCodeConstant.INVALID_TOKEN,
          sysReturnService.getMessage(AdminReturnCodeConstant.INVALID_TOKEN));
    } else if (e.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
      return R.error(
          AdminReturnCodeConstant.TOKEN_TIMEOUT,
          sysReturnService.getMessage(AdminReturnCodeConstant.TOKEN_TIMEOUT));
    } else if (e.getType().equals(NotLoginException.BE_REPLACED)) {
      return R.error(
          AdminReturnCodeConstant.BE_REPLACED,
          sysReturnService.getMessage(AdminReturnCodeConstant.BE_REPLACED));
    } else if (e.getType().equals(NotLoginException.KICK_OUT)) {
      return R.error(
          AdminReturnCodeConstant.KICK_OUT,
          sysReturnService.getMessage(AdminReturnCodeConstant.KICK_OUT));
    }
    return R.error(
        AdminReturnCodeConstant.NOT_TOKEN,
        sysReturnService.getMessage(AdminReturnCodeConstant.NOT_TOKEN));
  }

  /** 参数异常 */
  @ExceptionHandler(BindException.class)
  public R handleBindException(BindException e) {
    log.warn("参数校验失败, 异常简介为:{}", e.getMessage(), e);
    String returnCode = e.getBindingResult().getFieldError().getDefaultMessage();
    return R.error(
        CommReturnCodeConstant.CAPTCHA_HAS_EXPIRED, sysReturnService.getMessage(returnCode));
  }

  /** 参数异常 */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public R handleBindException(MethodArgumentNotValidException e) {
    log.warn("参数校验失败, 异常简介为:{}", e.getMessage(), e);
    String returnCode = e.getBindingResult().getFieldError().getDefaultMessage();
    return R.error(
        CommReturnCodeConstant.CAPTCHA_HAS_EXPIRED, sysReturnService.getMessage(returnCode));
  }

  /** 参数异常 */
  @ExceptionHandler(ConstraintViolationException.class)
  public R handleBindException(ConstraintViolationException e) {
    log.warn("参数校验失败, 异常简介为:{}", e.getMessage(), e);
    for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
      return R.error(
          CommReturnCodeConstant.CAPTCHA_HAS_EXPIRED,
          sysReturnService.getMessage(constraintViolation.getMessage()));
    }
    return null;
  }

  /** 请求方式不支持 */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public R handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    log.error("请求地址:{},不支持{}请求", requestURI, e.getMethod());
    return R.error(CommReturnCodeConstant.HTTP_REQUEST_METHOD_NOT_SUPPORTED, e.getMessage());
  }

  /** 拦截未知的运行时异常 */
  @ExceptionHandler(RuntimeException.class)
  public R handleRuntimeException(RuntimeException e, HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    log.error("请求地址:{},发生未知异常.", requestURI, e);
    return R.error(CommReturnCodeConstant.RUNTIME_EXCEPTION, e.getMessage());
  }

  /** 系统异常 */
  @ExceptionHandler(Exception.class)
  public R handleException(Exception e, HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    log.error("请求地址:{},发生系统异常.", requestURI, e);
    return R.error(CommReturnCodeConstant.EXCEPTION, e.getMessage());
  }
}
