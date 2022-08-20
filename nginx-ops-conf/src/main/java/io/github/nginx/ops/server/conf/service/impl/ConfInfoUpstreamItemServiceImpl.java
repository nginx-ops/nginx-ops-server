package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoUpstreamItem;
import io.github.nginx.ops.server.conf.mapper.ConfInfoUpstreamItemMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoUpstreamItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_upstream_item(nginx 负载均衡明细配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoUpstreamItemServiceImpl
    extends ServiceImpl<ConfInfoUpstreamItemMapper, ConfInfoUpstreamItem>
    implements ConfInfoUpstreamItemService {

  private final LambdaQueryWrapper<ConfInfoUpstreamItem> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public void deleteByUpstreamId(String upstreamId) {
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoUpstreamItem::getUpstreamId, upstreamId);
    this.remove(queryWrapper);
  }

  @Override
  public List<ConfInfoUpstreamItem> selectByUpstreamId(String upstreamId) {
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoUpstreamItem::getUpstreamId, upstreamId);
    return this.list(queryWrapper);
  }
}
