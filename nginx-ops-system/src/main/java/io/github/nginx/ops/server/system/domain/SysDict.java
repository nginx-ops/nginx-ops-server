package io.github.nginx.ops.server.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/** 系统设置-字典表 @TableName sys_dict */
@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SysDict implements Serializable {

  @TableField(exist = false)
  private static final long serialVersionUID = 1L;
  /** 自增ID */
  @TableId(type = IdType.AUTO)
  private Long id;
  /** 字典类型 */
  private String type;
  /** 字典编码 */
  private String code;
  /** 字典内容 */
  private String value;
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
