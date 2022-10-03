package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import io.github.nginx.ops.server.system.domain.SysSetting;
import io.github.nginx.ops.server.system.domain.dto.SysSettingDTO;
import io.github.nginx.ops.server.system.domain.query.SysSettingQuery;
import io.github.nginx.ops.server.system.mapper.SysSettingMapper;
import io.github.nginx.ops.server.system.service.SysSettingService;
import io.github.nginx.ops.server.system.service.SysUserSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author 24709
 * @description 针对表【sys_setting(系统设置)】的数据库操作Service实现
 * @createDate 2022-08-20 14:50:43
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysSettingServiceImpl extends ServiceImpl<SysSettingMapper, SysSetting>
    implements SysSettingService {

  private final SysUserSettingService sysUserSettingService;
  private final LambdaQueryWrapper<SysSetting> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public SysSetting getOneByLogin() {
    return this.getById("TEST");
  }

  @Override
  public void save(SysSettingDTO dto) {}

  @Override
  public void delete(String id) {}

  @Override
  public void update(String id, SysSettingDTO dto) {}

  @Override
  public List<SysSetting> list(SysSettingQuery query) {
    return null;
  }

  @Override
  public SysSettingDTO getOne(String id) {
    return null;
  }

  @Override
  public List<SysSettingDTO> selectByUserId(String id) {
    queryWrapper.clear();
    List<String> settingIds = sysUserSettingService.selectSettingIdListByUserId(id);
    if (ObjectUtil.isEmpty(settingIds)) {
      return Collections.emptyList();
    }
    queryWrapper.in(BaseEntity::getId, settingIds);
    List<SysSetting> list = this.list(queryWrapper);
    return BeanUtil.copyToList(list, SysSettingDTO.class);
  }
}
