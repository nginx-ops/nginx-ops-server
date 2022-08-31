package io.github.nginx.ops.server.system.domain.dto;

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
 * @date 2022/8/31 9:46
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleDTO implements Serializable {

  /** 唯一ID */
  private String id;
  /** 角色编码 */
  private String roleCode;
  /** 角色名称 */
  private String roleName;
  /** 排序 */
  private Integer order;
  /** 是否开启 */
  private Boolean isEnable;
  /** 备注 */
  private String remark;
}
