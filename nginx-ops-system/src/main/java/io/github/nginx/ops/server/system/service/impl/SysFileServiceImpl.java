package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.system.domain.SysFile;
import io.github.nginx.ops.server.system.domain.dto.SysFileDTO;
import io.github.nginx.ops.server.system.domain.query.SysFileQuery;
import io.github.nginx.ops.server.system.mapper.SysFileMapper;
import io.github.nginx.ops.server.system.service.SysFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

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
    // 随机生成盐值
    String salt = RandomUtil.randomString(5);
    File file =
        FileUtil.newFile(
            dto.getPath()
                + FileUtil.FILE_SEPARATOR
                + salt
                + "-"
                + multipartFile.getOriginalFilename());
    try {
      FileUtil.writeFromStream(multipartFile.getInputStream(), file);
    } catch (Exception e) {
      log.error("文件流获取失败, 异常信息为:{}", e.getMessage(), e);
      throw new BusinessException("文件上传失败!");
    }
    SysFile sysFile =
        SysFile.builder()
            .salt(salt)
            .fileName(multipartFile.getOriginalFilename())
            .filePath(dto.getPath())
            .fileType(multipartFile.getContentType())
            .fileSize(multipartFile.getSize())
            .build();
    this.save(sysFile);
    return sysFile;
  }

  @Override
  public void delete(String id) {
    // id不存在
    if (id == null || "".equals(id)) {
      throw new BusinessException("id不能为空!");
    } else {
      SysFile sysFile = baseMapper.selectById(id);
      String path = sysFile.getFilePath() + "/" + sysFile.getSalt() + "-" + sysFile.getFileName();
      // 删除文件
      FileUtil.del(new File(path));
      baseMapper.deleteById(id);
    }
  }

  @Override
  public void deleteBatch(List<String> ids) {
    if (CollectionUtils.isEmpty(ids)) {
      throw new BusinessException("id不能为空!");
    } else {
      List<SysFile> sysFiles = baseMapper.selectBatchIds(ids);
      sysFiles.stream()
          .forEach(
              item -> {
                this.delete(item.getId());
              });
      baseMapper.deleteBatchIds(ids);
    }
  }

  @Override
  public R selectPage(Long page, Long limit, SysFileQuery sysFileQuery) {
    QueryWrapper<SysFile> queryWrapper = new QueryWrapper<>();

    String fileName = sysFileQuery.getFileName();
    String fileType = sysFileQuery.getFileType();
    String fileDesc = sysFileQuery.getFileDesc();

    if (!StringUtils.isEmpty(fileName)) {
      queryWrapper.like("file_name", fileName);
    }

    if (!StringUtils.isEmpty(fileType)) {
      queryWrapper.eq("file_type", fileType);
    }

    if (!StringUtils.isEmpty(fileDesc)) {
      queryWrapper.like("file_desc", fileDesc);
    }

    Page<SysFile> pageParam = new Page<>(page, limit);
    Page<SysFile> sysFilePage = baseMapper.selectPage(pageParam, queryWrapper);

    return R.builder()
        .count(sysFilePage.getTotal())
        .data(sysFilePage.getRecords())
        .message("查询成功")
        .time(System.currentTimeMillis())
        .code(R.SUCCESS)
        .build();
  }
}
