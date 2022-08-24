package io.github.nginx.ops.server.system.domain;

import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 用户和设置的中间表
 *
 * @author lihao3
 * @date 2022/8/24 9:41
 */
@Setter
@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SysUserSetting extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 用户ID */
  private String userId;
  /** nginx管理ID */
  private String settingId;
  /** 备注 */
  private String remark;
  /** 逻辑删除 */
  private Boolean deleteFlag;
}
