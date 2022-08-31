package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysFile;
import io.github.nginx.ops.server.system.domain.dto.SysFileDTO;

/**
 * @author 24709
 * @description 针对表【sys_file】的数据库操作Service
 * @createDate 2022-08-22 12:19:46
 */
public interface SysFileService extends IService<SysFile> {

  /**
   * 上传文件
   *
   * @param dto
   * @return
   */
  SysFile upload(SysFileDTO dto);
}
