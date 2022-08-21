package io.github.nginx.ops.server.conf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoTemplate;
import io.github.nginx.ops.server.conf.domain.ConfInfoTemplateItem;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoTemplateDTO;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoTemplateItemDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoTemplateQuery;
import io.github.nginx.ops.server.conf.mapper.ConfInfoTemplateMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoTemplateItemService;
import io.github.nginx.ops.server.conf.service.ConfInfoTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_template(nginx配置模板表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoTemplateServiceImpl
    extends ServiceImpl<ConfInfoTemplateMapper, ConfInfoTemplate>
    implements ConfInfoTemplateService {

  private final ConfInfoTemplateItemService confInfoTemplateItemService;

  private final LambdaQueryWrapper<ConfInfoTemplate> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(ConfInfoTemplateDTO dto) {
    ConfInfoTemplate confInfoTemplate = BeanUtil.copyProperties(dto, ConfInfoTemplate.class);
    this.save(confInfoTemplate);
    List<ConfInfoTemplateItem> confInfoTemplateItemList =
        BeanUtil.copyToList(dto.getConfInfoTemplateItemList(), ConfInfoTemplateItem.class);
    confInfoTemplateItemList.forEach(item -> item.setTemplateId(confInfoTemplate.getId()));
    confInfoTemplateItemService.saveBatch(confInfoTemplateItemList);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(String id) {
    this.removeById(id);
    confInfoTemplateItemService.deleteByTemplateId(id);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateById(String id, ConfInfoTemplateDTO dto) {
    ConfInfoTemplate confInfoTemplate = BeanUtil.copyProperties(dto, ConfInfoTemplate.class);
    confInfoTemplate.setId(id);
    this.updateById(confInfoTemplate);
    confInfoTemplateItemService.deleteByTemplateId(id);
    List<ConfInfoTemplateItem> confInfoTemplateItemList =
        BeanUtil.copyToList(dto.getConfInfoTemplateItemList(), ConfInfoTemplateItem.class);
    confInfoTemplateItemList.forEach(item -> item.setTemplateId(confInfoTemplate.getId()));
    confInfoTemplateItemService.saveBatch(confInfoTemplateItemList);
  }

  @Override
  public List<ConfInfoTemplate> list(ConfInfoTemplateQuery query) {
    queryWrapper.clear();
    queryWrapper
        .eq(ObjectUtil.isNotEmpty(query.getType()), ConfInfoTemplate::getType, query.getType())
        .like(ObjectUtil.isNotEmpty(query.getName()), ConfInfoTemplate::getName, query.getName())
        .like(
            ObjectUtil.isNotEmpty(query.getRemark()),
            ConfInfoTemplate::getRemark,
            query.getRemark());
    return this.list(queryWrapper);
  }

  @Override
  public ConfInfoTemplateDTO getOne(String id) {
    ConfInfoTemplate confInfoTemplate = this.getById(id);
    List<ConfInfoTemplateItem> confInfoTemplateItemList =
        confInfoTemplateItemService.selectByTemplateId(id);
    ConfInfoTemplateDTO confInfoTemplateDTO =
        BeanUtil.copyProperties(confInfoTemplate, ConfInfoTemplateDTO.class);
    confInfoTemplateDTO.setConfInfoTemplateItemList(
        BeanUtil.copyToList(confInfoTemplateItemList, ConfInfoTemplateItemDTO.class));
    return confInfoTemplateDTO;
  }
}
