package io.github.nginx.ops.server.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/** 角色菜单关联表 @TableName sys_role_menu */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SysRoleMenu implements Serializable {
  private static final long serialVersionUID = 1L;
  /** 唯一ID */
  private String id;
  /** 角色ID */
  private String roleId;
  /** 菜单ID */
  private String menuId;
}
