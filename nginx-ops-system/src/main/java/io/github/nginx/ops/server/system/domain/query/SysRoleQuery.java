package io.github.nginx.ops.server.system.domain.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lihao3
 * @date 2022/8/31 9:59
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleQuery implements Serializable {

  /** 角色编码 */
  private String roleCode;
  /** 角色名称 */
  private String roleName;
  /** 是否开启 */
  private Boolean isEnable;
  /** 备注 */
  private String remark;
}
