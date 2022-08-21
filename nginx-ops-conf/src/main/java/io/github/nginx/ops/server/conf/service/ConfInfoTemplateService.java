package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfoTemplate;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoTemplateDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoTemplateQuery;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_template(nginx配置模板表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoTemplateService extends IService<ConfInfoTemplate> {

  /**
   * 保存
   *
   * @param dto
   */
  void save(ConfInfoTemplateDTO dto);

  /**
   * 删除
   *
   * @param id
   */
  void delete(String id);

  /**
   * 根据ID修改
   *
   * @param id
   * @param dto
   */
  void updateById(String id, ConfInfoTemplateDTO dto);

  /**
   * 查询列表
   *
   * @param query
   * @return
   */
  List<ConfInfoTemplate> list(ConfInfoTemplateQuery query);

  /**
   * 根据ID查询单条
   *
   * @param id
   * @return
   */
  ConfInfoTemplateDTO getOne(String id);
}
