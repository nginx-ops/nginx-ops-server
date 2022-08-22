package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.odiszapc.nginxparser.NgxBlock;
import io.github.nginx.ops.server.conf.domain.ConfInfoServer;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoServerDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoServerQuery;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoItemVO;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_server(nginx服务配置表)】的数据库操作Service
 * @createDate 2022-08-20 14:35:15
 */
public interface ConfInfoServerService extends IService<ConfInfoServer> {

  /**
   * 提交
   *
   * @param dto
   */
  void save(ConfInfoServerDTO dto);

  /**
   * 删除
   *
   * @param id
   */
  void delete(String id);

  /**
   * 修改ID修改
   *
   * @param id
   * @param dto
   */
  void updateById(String id, ConfInfoServerDTO dto);

  /**
   * 查询列表
   *
   * @param query
   * @return
   */
  List<ConfInfoServer> list(ConfInfoServerQuery query);

  /**
   * 查询单条信息
   *
   * @param id
   * @return
   */
  ConfInfoServerDTO getOne(String id);

  /**
   * 拼接nginx配置文件
   *
   * @param id
   * @return
   */
  NgxBlock buildBlockServer(String id);

  /**
   * 生成临时文件
   *
   * @return
   */
  List<ConfInfoItemVO> createTempFile();

  /**
   * 根据证书ID查询使用证书的数量
   *
   * @param certificateId
   * @return
   */
  long selectCountByCertificateId(String certificateId);
}
