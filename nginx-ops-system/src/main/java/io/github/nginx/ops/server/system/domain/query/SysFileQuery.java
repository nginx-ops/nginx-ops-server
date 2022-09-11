package io.github.nginx.ops.server.system.domain.query;

import lombok.*;

import java.io.Serializable;

/**
 * @BelongsProject: nginx-ops-server @BelongsPackage:
 * io.github.nginx.ops.server.system.domain.query @Author: wgy @CreateTime: 2022-09-11
 * 18:02:12 @Description: 文件查询条件
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SysFileQuery implements Serializable {

  /** 文件名 */
  private String fileName;
  /** 文件类型 */
  private String fileType;
  /** 文件备注 */
  private String fileDesc;
}
