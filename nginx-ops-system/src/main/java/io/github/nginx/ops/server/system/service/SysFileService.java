package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysFile;
import io.github.nginx.ops.server.system.domain.dto.SysFileDTO;
import io.github.nginx.ops.server.system.domain.query.SysFileQuery;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * @author 24709
 * @description 针对表【sys_file】的数据库操作Service
 * @createDate 2022-08-22 12:19:46
 */
public interface SysFileService extends IService<SysFile> {

  /**
   * 多文件上传
   *
   * @param dto
   */
  void upload(SysFileDTO dto);

  /**
   * 下载或预览文件
   *
   * @param id
   * @param preview
   */
  void download(String id, Boolean preview, HttpServletResponse response);

  /**
   * 多文件打包下载
   *
   * @param ids
   */
  void downloadZip(Set<String> ids, HttpServletResponse response);

  /**
   * 删除文件
   *
   * @param id
   */
  void delete(String id);

  /**
   * 查询文件列表
   *
   * @param query
   * @return
   */
  List<SysFile> list(SysFileQuery query);
}
