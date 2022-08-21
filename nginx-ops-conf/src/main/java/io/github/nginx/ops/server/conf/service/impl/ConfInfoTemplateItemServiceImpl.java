package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoTemplateItem;
import io.github.nginx.ops.server.conf.mapper.ConfInfoTemplateItemMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoTemplateItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_template_item(nginx配置模板表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoTemplateItemServiceImpl
    extends ServiceImpl<ConfInfoTemplateItemMapper, ConfInfoTemplateItem>
    implements ConfInfoTemplateItemService {

  private final LambdaQueryWrapper<ConfInfoTemplateItem> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public void deleteByTemplateId(String templateId) {
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoTemplateItem::getTemplateId, templateId);
    this.remove(queryWrapper);
  }

  @Override
  public List<ConfInfoTemplateItem> selectByTemplateId(String templateId) {
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoTemplateItem::getTemplateId, templateId);
    return this.list(queryWrapper);
  }
}
