package io.github.nginx.ops.server.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/** 系统设置 @TableName sys_setting */
@TableName(value = "sys_setting")
@Data
public class SysSetting implements Serializable {
  @TableField(exist = false)
  private static final long serialVersionUID = 1L;
  /** 唯一ID */
  @TableId private String id;
  /** 分组ID */
  private String groupId;
  /** 名称 */
  private String name;
  /** 地址 */
  private String url;
  /** 账号 */
  private String username;
  /** 密码 */
  private String password;
  /** 顺序 */
  private Integer order;
  /** nginx执行文件 */
  private String nginxExe;
  /** nginx启动命令 */
  private String nginxStartCmd;
  /** nginx重启命令 */
  private String nginxReloadCmd;
  /** nginx停止命令 */
  private String nginxStopCmd;
  /** nginx配置文件夹路径 */
  private String nginxConfPath;
  /** nginx主配置文件路径 */
  private String nginxMainConfPath;
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
