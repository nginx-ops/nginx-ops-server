package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysDict;
import io.github.nginx.ops.server.system.domain.query.SysDictQuery;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【sys_dict(系统设置-字典表)】的数据库操作Service
 * @createDate 2022-08-20 14:50:43
 */
public interface SysDictService extends IService<SysDict> {

  /**
   * 查询列表接口
   *
   * @param query
   * @return
   */
  List<SysDict> list(SysDictQuery query);
}
