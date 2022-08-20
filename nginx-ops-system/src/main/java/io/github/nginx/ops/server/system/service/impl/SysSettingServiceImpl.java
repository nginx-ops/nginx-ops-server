package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysSetting;
import io.github.nginx.ops.server.system.mapper.SysSettingMapper;
import io.github.nginx.ops.server.system.service.SysSettingService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【sys_setting(系统设置)】的数据库操作Service实现
 * @createDate 2022-08-20 14:50:43
 */
@Service
public class SysSettingServiceImpl extends ServiceImpl<SysSettingMapper, SysSetting>
    implements SysSettingService {}
