package io.github.nginx.ops.server.system.domain.query;

import lombok.*;

import java.io.Serializable;

/**
 * @Author: wgy @CreateTime: 2022-10-13 22:16:20 @Description:
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuQuery implements Serializable {

  /** 菜单名称 */
  private String menuName;
  /** 菜单状态（1正常 0停用） */
  private Boolean isEnable;
}
