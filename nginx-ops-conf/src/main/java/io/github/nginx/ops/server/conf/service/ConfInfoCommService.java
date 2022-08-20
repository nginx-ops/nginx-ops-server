package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfoComm;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoCommQuery;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

  /**
   * 根据其他ID删除
   *
   * @param otherId
   */
  void deleteByOtherId(String otherId);

  /**
   * 根据其他ID集合删除
   *
   * @param ids
   */
  void deleteByOtherId(Set<String> ids);

  /**
   * 根据其他ID查询列表
   *
   * @param otherId
   * @return
   */
  List<ConfInfoComm> selectByOtherId(String otherId);

  /**
   * 查询配置按照其他ID分组
   *
   * @param otherIds
   * @return
   */
  Map<String, List<ConfInfoComm>> getMap(Set<String> otherIds);

  /**
   * 预览
   *
   * @param type
   * @return
   */
  String preview(String type);
}
