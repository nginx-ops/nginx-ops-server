package io.github.nginx.ops.server.comm.domain.vo;

/**
 * 通用返回接口
 *
 * @author lihao3
 */
public interface ReturnService {

  /**
   * 返回编码
   *
   * @return
   */
  String getCode();

  /**
   * 返回中文信息
   *
   * @return
   */
  String getMessage();

  /**
   * 返回英语信息
   *
   * @return
   */
  String getEnMessage();
}
