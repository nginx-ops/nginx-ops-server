package io.github.nginx.ops.server.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wgy
 * @date 2022/8/31 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SysRoleDTO implements Serializable {

  /** 唯一ID */
  private String id;
  /** 角色编码 */
  private String roleCode;
  /** 角色名称 */
  private String roleName;
  /** 排序 */
  private Integer order;
  /** 是否开启 */
  private String isEnable;
  /** 创建时间 */
  private Date createTime;

  /** 备注 */
  private String remark;

  /** 该角色已选中菜单id列表 */
  private List<String> selectMenuIdList;

  /** 更新后的选中菜单列表 */
  private List<String> updateMenuIdList;
}
