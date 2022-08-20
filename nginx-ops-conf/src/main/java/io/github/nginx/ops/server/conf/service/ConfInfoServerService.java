package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfoServer;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoServerDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoServerQuery;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_server(nginx服务配置表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoServerService extends IService<ConfInfoServer> {

  /**
   * 提交
   *
   * @param dto
   */
  void save(ConfInfoServerDTO dto);

  /**
   * 删除
   *
   * @param id
   */
  void delete(String id);

  /**
   * 修改ID修改
   *
   * @param id
   * @param dto
   */
  void updateById(String id, ConfInfoServerDTO dto);

  /**
   * 查询列表
   *
   * @param query
   * @return
   */
  List<ConfInfoServer> list(ConfInfoServerQuery query);

  /**
   * 查询单条信息
   *
   * @param id
   * @return
   */
  ConfInfoServerDTO getOne(String id);

  /**
   * 预览
   *
   * @param id
   * @return
   */
  String preview(String id);
}
