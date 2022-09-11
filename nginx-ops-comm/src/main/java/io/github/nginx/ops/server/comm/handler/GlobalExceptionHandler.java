package io.github.nginx.ops.server.comm.handler;

import io.github.nginx.ops.server.comm.domain.vo.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author lihao3
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  // /** 业务异常 */
  // @ExceptionHandler(BusinessException.class)
  // public AjaxResult handleServiceException(ServiceException e, HttpServletRequest request) {
  //   log.error(e.getMessage(), e);
  //   Integer code = e.getCode();
  //   return StringUtils.isNotNull(code)
  //       ? AjaxResult.error(code, e.getMessage())
  //       : AjaxResult.error(e.getMessage());
  // }
  //
  // /** 拦截未知的运行时异常 */
  // @ExceptionHandler(RuntimeException.class)
  // public R handleRuntimeException(RuntimeException e, HttpServletRequest request) {
  //   String requestURI = request.getRequestURI();
  //   log.error("请求地址: {},发生未知异常.", requestURI, e);
  //   return R.error(e.getMessage());
  // }
  //
  // /** 系统异常 */
  // @ExceptionHandler(Exception.class)
  // public R handleException(Exception e, HttpServletRequest request) {
  //   String requestURI = request.getRequestURI();
  //   log.error("请求地址: {},发生系统异常.", requestURI, e);
  //   return R.error(e.getMessage());
  // }

  /** 自定义验证异常 */
  @ExceptionHandler(BindException.class)
  public R handleBindException(BindException e) {
    log.error(e.getMessage(), e);
    String message = e.getAllErrors().get(0).getDefaultMessage();
    return R.error(message, message);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public R handleBindException(MethodArgumentNotValidException e) {
    log.error(e.getMessage(), e);
    String message =
        messageSource.getMessage(
            e.getBindingResult().getFieldError(), LocaleContextHolder.getLocale());
    return R.error(message, message);
  }

  // /** 请求方式不支持 */
  // @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  // public R handleHttpRequestMethodNotSupported(
  //     HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
  //   String requestURI = request.getRequestURI();
  //   log.error("请求地址:{},不支持{}请求", requestURI, e.getMethod());
  //   return R.error(e.getMessage());
  // }
}
