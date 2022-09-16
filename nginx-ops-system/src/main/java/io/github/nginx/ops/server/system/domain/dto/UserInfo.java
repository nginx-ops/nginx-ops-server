package io.github.nginx.ops.server.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author lihao3
 * @date 2022/9/16 11:11
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

  /** 用户信息 */
  private SysUserDTO sysUser;
  /** 所选租户 */
  private SysSettingDTO selectedSetting;
  /** 所拥有的角色列表 */
  private List<SysRoleDTO> sysRoleList;
  /** 所拥有的项目列表 */
  private List<SysSettingDTO> sysSettingList;
}
