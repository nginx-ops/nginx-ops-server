package io.github.nginx.ops.server.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.nginx.ops.server.conf.domain.ConfInfoCertificate;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoCertificateDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoCertificateQuery;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_certificate(证书表)】的数据库操作Service
 * @createDate 2022-08-22 11:36:15
 */
public interface ConfInfoCertificateService extends IService<ConfInfoCertificate> {

  /**
   * 新增
   *
   * @param dto
   */
  void save(ConfInfoCertificateDTO dto);

  /**
   * 删除
   *
   * @param id
   */
  void delete(String id);

  /**
   * 根据ID修改
   *
   * @param id
   * @param dto
   */
  void updateById(String id, ConfInfoCertificateDTO dto);

  /**
   * 查询列表
   *
   * @param query
   * @return
   */
  List<ConfInfoCertificate> list(ConfInfoCertificateQuery query);

  /**
   * 查询单条
   *
   * @param id
   * @return
   */
  ConfInfoCertificateDTO getOne(String id);
}
