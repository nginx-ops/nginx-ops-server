package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfoItem;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_item(nginx明细配置表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoItemService extends IService<ConfInfoItem> {

  /**
   * 根据配置文件头表查询明细表
   *
   * @param id
   * @return
   */
  List<ConfInfoItem> selectByConfId(String id);

  /**
   * 根据头表ID删除
   *
   * @param id
   */
  void removeByConfId(String id);
}
