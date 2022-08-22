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
 * 文件表
 *
 * @author 24709 @TableName sys_file
 */
@Setter
@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SysFile extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /** 文件名称 */
  private String fileName;
  /** 文件描述 */
  private String fileDesc;
  /** 文件路径 */
  private String filePath;
  /** 文件类型 */
  private String fileType;
  /** 文件大小 */
  private Long fileSize;
  /** 备注 */
  private String remark;
}
