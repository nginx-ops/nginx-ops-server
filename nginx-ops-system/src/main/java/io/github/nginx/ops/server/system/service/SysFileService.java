package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.system.domain.SysFile;
import io.github.nginx.ops.server.system.domain.dto.SysFileDTO;
import io.github.nginx.ops.server.system.domain.query.SysFileQuery;

import java.util.List;

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

  /**
   * @description: 删除文件
   * @author: wgy
   * @date: 2022/9/11 16:41
   * @param: id
   */
  void delete(String id);

  /**
   * @description: 批量删除文件
   * @author: wgy
   * @date: 2022/9/11 16:42
   * @param: ids
   */
  void deleteBatch(List<String> ids);

  /**
   * @description: 分页查询文件
   * @author: wgy
   * @date: 2022/9/11 18:08
   * @param: page
   * @param: limit
   * @param: sysFileQuery
   */
  R selectPage(Long page, Long limit, SysFileQuery sysFileQuery);
}
