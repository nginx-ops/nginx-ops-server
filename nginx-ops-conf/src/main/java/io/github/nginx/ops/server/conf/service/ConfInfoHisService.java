package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfoHis;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoHisQuery;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoVO;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_his(nginx配置历史表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoHisService extends IService<ConfInfoHis> {

  /**
   * 回滚配置文件
   *
   * @param id
   */
  void back(String id);

  /**
   * 根据条件查询列表
   *
   * @param query
   * @return
   */
  List<ConfInfoHis> list(ConfInfoHisQuery query);

  /**
   * 查询单条信息
   *
   * @param id
   * @return
   */
  ConfInfoVO getOne(String id);
}
