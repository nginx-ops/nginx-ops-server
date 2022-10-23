package io.github.nginx.ops.server.system.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @Author: wgy @CreateTime: 2022-10-17 21:39:05 @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SysMenuDTO implements Serializable {

  /** 唯一ID */
  @TableId private String id;

  /** 菜单名称 */
  private String menuName;
  /** 父菜单ID */
  private String parentId;
  /** 显示顺序 */
  @TableField("`order`")
  private Integer order;
  /** 路由地址 */
  private String path;
  /** 组件路径 */
  private String component;
  /** 路由参数 */
  @TableField("`query`")
  private String query;
  /** 是否为外链（1是 0否） */
  private String isFrame;
  /** 是否缓存（1缓存 0不缓存） */
  private String isCache;
  /** 菜单类型（M目录 C菜单 F按钮） */
  private String menuType;
  /** 菜单状态（1正常 0停用） */
  private String isEnable;
  /** 权限标识 */
  private String perms;
  /** 菜单图标 */
  private String icon;
}
