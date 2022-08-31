package io.github.nginx.ops.server.system.domain.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lihao3
 * @date 2022/8/27
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SysUserQuery implements Serializable {

  /** 登陆账号 */
  private String loginName;
  /** 昵称 */
  private String nickName;
  /** 邮箱 */
  private String email;
  /** 是否启用 */
  private Boolean isEnable;
  /** 开始最后登录时间 */
  private Date startLoginDate;
  /** 截止最后登录时间 */
  private Date endLoginDate;
  /** 开始最后登录时间 */
  private Date startCreatTime;
  /** 截止最后登录时间 */
  private Date endCreatTime;
}
