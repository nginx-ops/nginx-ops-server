package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoItem;
import io.github.nginx.ops.server.conf.mapper.ConfInfoItemMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_item(nginx明细配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoItemServiceImpl extends ServiceImpl<ConfInfoItemMapper, ConfInfoItem>
    implements ConfInfoItemService {

  private final LambdaQueryWrapper<ConfInfoItem> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public List<ConfInfoItem> selectByConfId(String id) {
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoItem::getConfId, id);
    return this.list(queryWrapper);
  }

  @Override
  public void removeByConfId(String id) {
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoItem::getConfId, id);
    this.remove(queryWrapper);
  }
}
