package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysSetting;

/**
 * @author 24709
 * @description 针对表【sys_setting(系统设置)】的数据库操作Service
 * @createDate 2022-08-20 14:50:43
 */
public interface SysSettingService extends IService<SysSetting> {

  /**
   * 获取登录人获选的设置文件
   *
   * @return
   */
  SysSetting getOneByLogin();
}
