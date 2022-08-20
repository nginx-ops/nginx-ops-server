package io.github.nginx.ops.server.conf.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoComm;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoCommQuery;
import io.github.nginx.ops.server.conf.mapper.ConfInfoCommMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoCommService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_comm(nginx通用配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoCommServiceImpl extends ServiceImpl<ConfInfoCommMapper, ConfInfoComm>
    implements ConfInfoCommService {

  private final LambdaQueryWrapper<ConfInfoComm> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public List<ConfInfoComm> list(ConfInfoCommQuery query) {
    queryWrapper.clear();
    queryWrapper
        .eq(ObjectUtil.isNotEmpty(query.getOtherId()), ConfInfoComm::getOtherId, query.getOtherId())
        .eq(ObjectUtil.isNotEmpty(query.getType()), ConfInfoComm::getType, query.getType())
        .like(ObjectUtil.isNotEmpty(query.getName()), ConfInfoComm::getName, query.getName())
        .like(ObjectUtil.isNotEmpty(query.getRemark()), ConfInfoComm::getRemark, query.getRemark());
    return this.list(queryWrapper);
  }
}
