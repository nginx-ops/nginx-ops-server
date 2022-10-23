package io.github.nginx.ops.server.system.domain.query;

import io.github.nginx.ops.server.comm.domain.query.BaseQuery;
import lombok.*;

import java.io.Serializable;

/**
 * @author wgy
 * @date 2022/8/31 9:59
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleQuery extends BaseQuery implements Serializable {

  /** 角色编码 */
  private String roleCode;
  /** 角色名称 */
  private String roleName;
  /** 是否开启 */
  private String isEnable;
  /** 开始时间 */
  private String beginTime;
  /** 结束时间 */
  private String endTime;
}
