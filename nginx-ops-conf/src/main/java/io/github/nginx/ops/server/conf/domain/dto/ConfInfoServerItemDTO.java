package io.github.nginx.ops.server.conf.domain.dto;

import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/20
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoServerItemDTO implements Serializable {

  /** 顺序 */
  @TableField("`order`")
  @OrderBy(asc = true)
  private Integer order;
  /** 监控路径 */
  private String path;
  /** 监控类型 */
  private String type;
  /** 监控地址 */
  private String value;
  /** 是否携带HOST参数 */
  private Boolean isHeader;
  /** 是否开启websocket */
  private Boolean isWebsocket;
  /** 路由模式 */
  private String rootType;
  /** 路由路径 */
  private String rootPath;
  /** 路由默认页 */
  private String rootIndex;
  /** 备注 */
  private String remark;
  /** 其他参数 */
  private List<ConfInfoCommDTO> confInfoCommList;
}
