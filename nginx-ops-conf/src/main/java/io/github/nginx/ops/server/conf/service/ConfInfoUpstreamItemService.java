package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfoUpstreamItem;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_upstream_item(nginx 负载均衡明细配置表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoUpstreamItemService extends IService<ConfInfoUpstreamItem> {

  /**
   * 根据负载均衡头表ID删除明细表
   *
   * @param upstreamId
   */
  void deleteByUpstreamId(String upstreamId);

  /**
   * 根据负载均衡头表ID查询列表
   *
   * @param upstreamId
   * @return
   */
  List<ConfInfoUpstreamItem> selectByUpstreamId(String upstreamId);
}
