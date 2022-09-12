package io.github.nginx.ops.server.comm.domain.vo;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author lihao3
 * @since 2022/4/25
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("通用返回实体类")
public class R<T> implements Serializable {

  /** 成功 */
  public static final String SUCCESS = "00000";
  /** 成功 */
  public static final String ERROR = "500";

  /** 编码 */
  @ApiModelProperty("返回编码")
  private String code;
  /** 内容 */
  @ApiModelProperty("提示内容")
  private String message;
  /** 反馈时间 */
  @ApiModelProperty("反馈时间")
  private long time;
  /** 返回数据 */
  @ApiModelProperty("返回数据")
  private T data;
  /** 总条数 */
  @ApiModelProperty("总条数")
  private long count;

  public static R success(String message) {
    return R.builder()
        .code(SUCCESS)
        .message(message)
        .time(System.currentTimeMillis())
        .count(0L)
        .build();
  }

  public static <T> R<T> success(String message, T data) {
    return (R<T>)
        R.builder()
            .code(SUCCESS)
            .message(message)
            .time(System.currentTimeMillis())
            .data(data)
            .count(data instanceof Collection ? ((Collection<?>) data).size() : 1L)
            .build();
  }

  public static <T> R<T> success(String message, Page<T> data) {
    return (R<T>)
        R.builder()
            .code(SUCCESS)
            .message(message)
            .time(System.currentTimeMillis())
            .data(data.getResult())
            .count(data.getTotal())
            .build();
  }

  public static R error(String code, String message) {
    return R.builder().code(code).message(message).time(System.currentTimeMillis()).build();
  }

  public static R error(String message) {
    return R.builder().code(ERROR).message(message).time(System.currentTimeMillis()).build();
  }
}
