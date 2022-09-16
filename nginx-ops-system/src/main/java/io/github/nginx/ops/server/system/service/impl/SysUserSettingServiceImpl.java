package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysUserSetting;
import io.github.nginx.ops.server.system.mapper.SysUserSettingMapper;
import io.github.nginx.ops.server.system.service.SysUserSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lihao3
 * @date 2022/8/24 9:41
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserSettingServiceImpl extends ServiceImpl<SysUserSettingMapper, SysUserSetting>
    implements SysUserSettingService {

  private final LambdaQueryWrapper<SysUserSetting> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public List<String> selectSettingIdListByUserId(String userId) {
    queryWrapper.clear();
    queryWrapper.eq(SysUserSetting::getUserId, userId);
    return new ArrayList<>(
        this.list(queryWrapper).stream()
            .map(SysUserSetting::getSettingId)
            .collect(Collectors.toSet()));
  }
}
