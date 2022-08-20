package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import io.github.nginx.ops.server.conf.domain.ConfInfoServerItem;
import io.github.nginx.ops.server.conf.mapper.ConfInfoServerItemMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoCommService;
import io.github.nginx.ops.server.conf.service.ConfInfoServerItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 24709
 * @description 针对表【conf_info_server_item(nginx服务明细配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoServerItemServiceImpl
    extends ServiceImpl<ConfInfoServerItemMapper, ConfInfoServerItem>
    implements ConfInfoServerItemService {

  private final ConfInfoCommService confInfoCommService;

  private final LambdaQueryWrapper<ConfInfoServerItem> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteByServerId(String serverId) {
    // 拼接where条件
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoServerItem::getServerId, serverId);
    // 查询列表
    List<ConfInfoServerItem> list = this.list(queryWrapper);
    // 获取ID集合
    Set<String> ids = list.stream().map(BaseEntity::getId).collect(Collectors.toSet());
    // 删除明细表
    this.remove(queryWrapper);
    // 删除明细表的其他参数
    confInfoCommService.deleteByOtherId(ids);
  }

  @Override
  public List<ConfInfoServerItem> selectByServerId(String serverId) {
    // 拼接where条件
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoServerItem::getServerId, serverId);
    return this.list(queryWrapper);
  }
}
