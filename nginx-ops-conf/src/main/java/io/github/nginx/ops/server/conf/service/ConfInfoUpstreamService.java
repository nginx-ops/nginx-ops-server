package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.odiszapc.nginxparser.NgxBlock;
import io.github.nginx.ops.server.conf.domain.ConfInfoUpstream;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoUpstreamDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoUpstreamQuery;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_upstream(nginx 负载均衡头表配置表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoUpstreamService extends IService<ConfInfoUpstream> {

  /**
   * 提交
   *
   * @param dto
   */
  void save(ConfInfoUpstreamDTO dto);

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
  void updateById(String id, ConfInfoUpstreamDTO dto);

  /**
   * 查询列表
   *
   * @param query
   * @return
   */
  List<ConfInfoUpstream> list(ConfInfoUpstreamQuery query);

  /**
   * 查询单条
   *
   * @param id
   * @return
   */
  ConfInfoUpstreamDTO getOne(String id);

  /**
   * 预览
   *
   * @param id
   * @return
   */
  String preview(String id);

  /**
   * 拼接负载均衡配置文件
   *
   * @param confInfoUpstreamDTO
   * @return
   */
  NgxBlock buildBlockUpstream(ConfInfoUpstreamDTO confInfoUpstreamDTO);
}
