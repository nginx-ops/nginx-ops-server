package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfoServerItem;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_server_item(nginx服务明细配置表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoServerItemService extends IService<ConfInfoServerItem> {

  /**
   * 根据反向代理头表ID删除明细表
   *
   * @param serverId
   */
  void deleteByServerId(String serverId);

  /**
   * 根据头表ID查询明细表
   *
   * @param serverId
   * @return
   */
  List<ConfInfoServerItem> selectByServerId(String serverId);
}
