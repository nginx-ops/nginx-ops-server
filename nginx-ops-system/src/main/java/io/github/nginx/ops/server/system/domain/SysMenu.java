package io.github.nginx.ops.server.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/** 菜单权限表 @TableName sys_menu */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(value = "handler")
public class SysMenu extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  /** 菜单ID */
  @TableId private String id;
  /** 菜单名称 */
  private String menuName;
  /** 父菜单ID */
  private String parentId;
  /** 显示顺序 */
  private Integer order;
  /** 路由地址 */
  private String path;
  /** 组件路径 */
  private String component;
  /** 路由参数 */
  private String query;
  /** 是否为外链（1是 0否） */
  private Boolean isFrame;
  /** 是否缓存（1缓存 0不缓存） */
  private Boolean isCache;
  /** 菜单类型（M目录 C菜单 F按钮） */
  private String menuType;
  /** 菜单状态（1正常 0停用） */
  private Boolean isEnable;
  /** 权限标识 */
  private String perms;
  /** 菜单图标 */
  private String icon;
  /** 备注 */
  private String remark;
  /** 子菜单列表 */
  private List<SysMenu> childrenList;
}
