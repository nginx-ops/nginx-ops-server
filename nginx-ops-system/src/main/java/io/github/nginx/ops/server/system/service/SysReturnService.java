package io.github.nginx.ops.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.system.domain.SysReturn;
import io.github.nginx.ops.server.system.domain.vo.SysReturnVO;

import java.util.List;
import java.util.Map;

/**
 * @author 24709
 * @description 针对表【sys_return】的数据库操作Service
 * @createDate 2022-09-11 14:16:01
 */
public interface SysReturnService extends IService<SysReturn> {

  /**
   * 获取返回值
   *
   * @param code
   * @return
   */
  String getMessage(String code);

  /**
   * 获取返回值
   *
   * @param code
   * @param params
   * @return
   */
  String getMessage(String code, Object... params);

  /**
   * 根据CODE获取返回实体
   *
   * @param code
   * @return
   */
  SysReturnVO getOneByCode(String code);

  /**
   * 转原始list转MAP<CODE,VO></>
   * @param sysReturnList
   * @return
   */
  Map<String, String> SysReturnListToMap(List<SysReturn> sysReturnList);
}
