package io.github.nginx.ops.server.comm.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lihao3
 * @date 2022/8/18
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

  /** 唯一ID */
  @TableId private String id;
  /** 创建人 */
  @TableField(fill = FieldFill.INSERT)
  private String createBy;
  /** 创建时间 */
  @TableField(fill = FieldFill.INSERT)
  private Date createTime;
  /** 修改人 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updateBy;
  /** 修改时间 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  @OrderBy
  private Date updateTime;
}
