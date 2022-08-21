package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfoTemplateItem;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_template_item(nginx配置模板表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoTemplateItemService extends IService<ConfInfoTemplateItem> {

  /**
   * 根据模板ID删除
   *
   * @param templateId
   */
  void deleteByTemplateId(String templateId);

  /**
   * 根据模板ID查询列表
   *
   * @param templateId
   * @return
   */
  List<ConfInfoTemplateItem> selectByTemplateId(String templateId);
}
