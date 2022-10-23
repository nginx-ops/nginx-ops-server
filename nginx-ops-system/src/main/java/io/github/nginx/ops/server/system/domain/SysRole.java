package io.github.nginx.ops.server.system.domain;

import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/** 角色表 @TableName sys_role */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SysRole extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 角色编码 */
  private String roleCode;
  /** 角色名称 */
  private String roleName;
  /** 排序 */
  @TableField("`order`")
  @OrderBy(asc = true)
  private Integer order;
  /** 是否开启 */
  private String isEnable;
  /** 备注 */
  private String remark;
  /** 逻辑删除 */
  private Boolean deleteFlag;
}
