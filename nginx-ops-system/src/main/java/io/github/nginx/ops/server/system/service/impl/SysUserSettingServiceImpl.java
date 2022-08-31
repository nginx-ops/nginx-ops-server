package io.github.nginx.ops.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysUserSetting;
import io.github.nginx.ops.server.system.mapper.SysUserSettingMapper;
import io.github.nginx.ops.server.system.service.SysUserSettingService;
import org.springframework.stereotype.Service;
/**
 * @author lihao3
 * @date 2022/8/24 9:41
 */
@Service
public class SysUserSettingServiceImpl extends ServiceImpl<SysUserSettingMapper, SysUserSetting>
    implements SysUserSettingService {}
