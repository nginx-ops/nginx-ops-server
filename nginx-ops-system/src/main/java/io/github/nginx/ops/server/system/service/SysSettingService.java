package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysSetting;
import io.github.nginx.ops.server.system.domain.dto.SysSettingDTO;
import io.github.nginx.ops.server.system.domain.query.SysSettingQuery;

import java.util.List;

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

  /**
   * 保存
   *
   * @param dto
   */
  void save(SysSettingDTO dto);

  /**
   * 删除
   *
   * @param id
   */
  void delete(String id);

  /**
   * 修改
   *
   * @param id
   * @param dto
   */
  void update(String id, SysSettingDTO dto);

  /**
   * 查询
   *
   * @param query
   * @return
   */
  List<SysSetting> list(SysSettingQuery query);

  /**
   * 查询单条
   *
   * @param id
   * @return
   */
  SysSettingDTO getOne(String id);
}
