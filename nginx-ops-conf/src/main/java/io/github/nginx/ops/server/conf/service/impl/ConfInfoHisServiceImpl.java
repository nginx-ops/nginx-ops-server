package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoHis;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoHisQuery;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoVO;
import io.github.nginx.ops.server.conf.mapper.ConfInfoHisMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoHisItemService;
import io.github.nginx.ops.server.conf.service.ConfInfoHisService;
import io.github.nginx.ops.server.conf.service.ConfInfoItemService;
import io.github.nginx.ops.server.conf.service.ConfInfoService;
import io.github.nginx.ops.server.system.domain.SysSetting;
import io.github.nginx.ops.server.system.service.SysSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_his(nginx配置历史表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoHisServiceImpl extends ServiceImpl<ConfInfoHisMapper, ConfInfoHis>
    implements ConfInfoHisService {

  private final ConfInfoService confInfoService;
  private final ConfInfoItemService confInfoItemService;
  private final ConfInfoHisItemService confInfoHisItemService;
  private final SysSettingService sysSettingService;
  private final LambdaQueryWrapper<ConfInfoHis> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void back(String id) {
    // 获取当前nginx的基本信息
    SysSetting sysSetting = sysSettingService.getOneByLogin();
    // 查询目前生效的配置文件
    ConfInfoVO confInfoVO = confInfoService.get();
  }

  @Override
  public List<ConfInfoHis> list(ConfInfoHisQuery query) {
    return null;
  }

  @Override
  public ConfInfoVO getOne(String id) {
    return null;
  }
}
