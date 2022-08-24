package io.github.nginx.ops.server.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
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
public class SysUser extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 登陆账号 */
  private String loginName;
  /** 密码 */
  @TableField(value = "`password`")
  private String password;
  /** 昵称 */
  private String nickName;
  /** 邮箱 */
  private String email;
  /** 头像 */
  private String avatar;
  /** 是否启用 */
  private Boolean isEnable;
  /** 最后登录IP */
  private String loginIp;
  /** 最后登录时间 */
  private Date loginDate;
  /** 是否删除 */
  private Boolean deleteFlag;
}
