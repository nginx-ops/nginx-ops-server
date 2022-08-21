package io.github.nginx.ops.server.conf.domain.vo;

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
public class ConfInfoVO implements Serializable {

  /** 配置文件 */
  private String id;
  /** 配置文件 */
  private String content;
  /** 按照域名拆分文件列表 */
  private List<ConfInfoItemVO> confInfoItemList;
}
