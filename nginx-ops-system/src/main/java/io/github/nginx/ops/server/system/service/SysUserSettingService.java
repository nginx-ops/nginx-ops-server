package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysUserSetting;

import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/24 9:41
 */
public interface SysUserSettingService extends IService<SysUserSetting> {

  /**
   * 根据用户ID查询用户拥有的nginx实例
   *
   * @param userId
   * @return
   */
  List<String> selectSettingIdListByUserId(String userId);
}
