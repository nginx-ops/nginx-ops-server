package io.github.nginx.ops.server.comm.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lihao3
 * @since 2022/4/25
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("通用查询实体类")
public class BaseQuery implements Serializable {

  /** 页码 */
  @ApiModelProperty("页码")
  private Integer pageNum = 1;
  /** 页数 */
  @ApiModelProperty("页数")
  private Integer pageSize = 20;
  /** 排序 */
  @ApiModelProperty("排序")
  private String orderBy;
}
