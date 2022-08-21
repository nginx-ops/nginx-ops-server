package io.github.nginx.ops.server.conf.domain.dto;

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
public class ConfInfoTemplateDTO implements Serializable {

  /** 类型 */
  private String type;
  /** 名称 */
  private String name;
  /** 备注 */
  private String remark;
  /** 排序 */
  private Integer order;
  /** 明细 */
  private List<ConfInfoTemplateItemDTO> confInfoTemplateItemList;
}
