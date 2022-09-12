package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysFile;
import io.github.nginx.ops.server.system.domain.dto.SysFileDTO;
import io.github.nginx.ops.server.system.domain.query.SysFileQuery;
import io.github.nginx.ops.server.system.enums.ContentDispositionEnums;
import io.github.nginx.ops.server.system.mapper.SysFileMapper;
import io.github.nginx.ops.server.system.service.SysFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

  private final LambdaQueryWrapper<SysFile> queryWrapper = new LambdaQueryWrapper<>();

  /**
   * 初始化返回头
   *
   * @param fileName
   * @param contentType
   * @param contentDispositionEnums
   * @param response
   * @throws UnsupportedEncodingException
   */
  private void initResponse(
      String fileName,
      String contentType,
      ContentDispositionEnums contentDispositionEnums,
      HttpServletResponse response)
      throws UnsupportedEncodingException {
    response.reset();
    response.setHeader(
        "Content-Disposition",
        StrUtil.format(
            "{};filename={}",
            contentDispositionEnums.getCode(),
            URLEncoder.encode(fileName, "UTF-8")));
    response.setContentType(contentType);
    response.setCharacterEncoding("utf-8");
  }

  @Override
  public void upload(SysFileDTO dto) {
    List<SysFile> sysFileList = new ArrayList<>();
    dto.getFiles()
        .forEach(
            file -> {
              try (InputStream inputStream = file.getInputStream()) {
                FileUtil.writeFromStream(
                    inputStream,
                    FileUtil.newFile(
                        FileUtil.normalize(
                            dto.getPath() + FileUtil.FILE_SEPARATOR + file.getOriginalFilename())));
                sysFileList.add(
                    SysFile.builder()
                        .fileName(file.getOriginalFilename())
                        .filePath(
                            FileUtil.normalize(
                                dto.getPath()
                                    + FileUtil.FILE_SEPARATOR
                                    + file.getOriginalFilename()))
                        .fileType(file.getContentType())
                        .fileSize(file.getSize())
                        .remark(dto.getRemark())
                        .build());
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            });
    this.saveBatch(sysFileList);
  }

  @Override
  public void download(String id, Boolean preview, HttpServletResponse response) {
    SysFile sysFile = this.getById(id);
    try (ServletOutputStream outputStream = response.getOutputStream()) {
      // 初始化请求
      this.initResponse(
          sysFile.getFileName(),
          sysFile.getFileType(),
          Boolean.TRUE.equals(preview)
              ? ContentDispositionEnums.INLINE
              : ContentDispositionEnums.ATTACHMENT,
          response);
      // 将文件内容写入请求头中
      FileUtil.writeToStream(sysFile.getFilePath(), outputStream);
    } catch (Exception e) {
      log.error("文件下载失败, 失败原因为:{}", e.getMessage(), e);
    }
  }

  @Override
  public void downloadZip(Set<String> ids, HttpServletResponse response) {
    List<SysFile> sysFileList = this.listByIds(ids);
    File[] fileList = new File[sysFileList.size()];
    for (int i = 0; i < sysFileList.size(); i++) {
      SysFile sysFile = sysFileList.get(i);
      fileList[i] = FileUtil.newFile(sysFile.getFilePath());
    }
    // 创建零时文件用于下载ZIP
    File zipFile = FileUtil.createTempFile("打包下载", ".zip", true);
    ZipUtil.zip(zipFile, false, fileList);
    try (ServletOutputStream outputStream = response.getOutputStream()) {
      // 初始化请求
      this.initResponse(
          "打包下载.zip", "application/zip", ContentDispositionEnums.ATTACHMENT, response);
      // 将文件内容写入请求头中
      FileUtil.writeToStream(zipFile, outputStream);
    } catch (Exception e) {
      log.error("文件下载失败, 失败原因为:{}", e.getMessage(), e);
    }
  }

  @Override
  public void delete(String id) {
    SysFile sysFile = this.getById(id);
    FileUtil.del(sysFile.getFilePath());
    this.removeById(id);
  }

  @Override
  public List<SysFile> list(SysFileQuery query) {
    queryWrapper.clear();
    queryWrapper
        .like(ObjectUtil.isEmpty(query.getFileName()), SysFile::getFileName, query.getFileName())
        .like(ObjectUtil.isEmpty(query.getRemark()), SysFile::getRemark, query.getRemark());
    return this.list(queryWrapper);
  }
}
