package io.github.nginx.ops.server.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author lihao3
 * @date 2022/8/22
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SysFileDTO implements Serializable {

  /** 存储路径 */
  private String path;
  /** 文件类型 */
  private String fileType;
  /** 文件 */
  private MultipartFile file;
}
