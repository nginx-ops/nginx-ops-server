package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfo;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoVO;
import io.github.nginx.ops.server.conf.domain.vo.FileVo;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info(nginx配置表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoService extends IService<ConfInfo> {

  /**
   * 获取磁盘列表
   *
   * @param pid
   * @return
   */
  List<FileVo> nodeList(String pid);

  /**
   * 预览
   *
   * @param type
   * @param id
   * @return
   */
  ConfInfoVO preview(String type, String id);

  /**
   * 测试配置文件
   *
   * @return
   * @param confInfoVO
   */
  String test(ConfInfoVO confInfoVO);

  /**
   * 替换配置文件到正式环境中
   *
   * @param confInfoVO
   */
  void replace(ConfInfoVO confInfoVO);

  /**
   * nginx 重新装配
   *
   * @return
   */
  String reload();

  /**
   * 停止nginx
   *
   * @return
   */
  String stop();

  /**
   * 启动nginx
   *
   * @return
   */
  String run();

  /**
   * 查看nginx状态
   *
   * @return
   */
  Boolean status();

  /**
   * 获取当前运行的配置文件
   *
   * @return
   */
  ConfInfoVO get();
}
