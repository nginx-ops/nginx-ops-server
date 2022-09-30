package io.github.nginx.ops.server.system.domain.query;

import io.github.nginx.ops.server.comm.domain.query.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 数据字典查询实体类
 *
 * @author lihao3
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SysDictQuery extends BaseQuery implements Serializable {

  /** 字典类型 */
  private String type;
  /** 字典编码 */
  private String code;
  /** 字典内容 */
  private String value;
  /** 备注 */
  private String remark;
}
