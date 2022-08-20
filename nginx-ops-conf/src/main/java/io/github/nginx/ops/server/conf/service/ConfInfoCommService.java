package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfoComm;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoCommQuery;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_comm(nginx通用配置表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoCommService extends IService<ConfInfoComm> {

  /**
   * 查询列表
   *
   * @param query
   * @return
   */
  List<ConfInfoComm> list(ConfInfoCommQuery query);
}
