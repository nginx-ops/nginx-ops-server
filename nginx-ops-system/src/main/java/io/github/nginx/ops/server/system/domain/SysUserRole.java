package io.github.nginx.ops.server.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/** 用户角色关联表 @TableName sys_user_role */
@TableName(value = "sys_user_role")
@Data
public class SysUserRole implements Serializable {
  @TableField(exist = false)
  private static final long serialVersionUID = 1L;
  /** 唯一ID */
  @TableId private String id;
  /** 用户ID */
  private String userId;
  /** 角色ID */
  private String roleId;
  /** 备注 */
  private String remark;
  /** 创建人 */
  private String createBy;
  /** 创建时间 */
  private Date createTime;
  /** 修改人 */
  private String updateBy;
  /** 修改时间 */
  private Date updateTime;
  /** 逻辑删除 */
  private Boolean deleteFlag;
}
