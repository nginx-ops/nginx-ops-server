package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.system.domain.SysFile;
import io.github.nginx.ops.server.system.domain.dto.SysFileDTO;
import io.github.nginx.ops.server.system.mapper.SysFileMapper;
import io.github.nginx.ops.server.system.service.SysFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author 24709
 * @description 针对表【sys_file】的数据库操作Service实现
 * @createDate 2022-08-22 12:19:46
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile>
    implements SysFileService {

  @Override
  public SysFile upload(SysFileDTO dto) {
    MultipartFile multipartFile = dto.getFile();
    File file = FileUtil.newFile(dto.getPath() + FileUtil.FILE_SEPARATOR + multipartFile.getName());
    try {
      FileUtil.writeFromStream(multipartFile.getInputStream(), file);
    } catch (Exception e) {
      log.error("文件流获取失败, 异常信息为:{}", e.getMessage(), e);
      throw new BusinessException("文件上传失败!");
    }
    SysFile sysFile =
        SysFile.builder()
            .fileName(multipartFile.getOriginalFilename())
            .filePath(dto.getPath())
            .fileType(multipartFile.getContentType())
            .fileSize(multipartFile.getSize())
            .build();
    this.save(sysFile);
    return sysFile;
  }
}
