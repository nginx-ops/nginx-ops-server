package io.github.nginx.ops.server.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author lihao3
 * @date 2022/8/24 10:03
 */
@Setter
@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDTO implements Serializable {

  /** 登陆账号 */
  private String loginName;
  /** 密码 */
  private String password;
  /** 昵称 */
  private String nickName;
  /** 邮箱 */
  private String email;
  /** 头像 */
  private String avatar;
  /** 备注 */
  private String remark;

}
