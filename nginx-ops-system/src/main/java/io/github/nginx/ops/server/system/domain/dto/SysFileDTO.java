package io.github.nginx.ops.server.system.domain.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

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
  /** 文件 */
  @JSONField(serialize = false)
  private List<MultipartFile> files;
  /** 备注 */
  private String remark;
}
