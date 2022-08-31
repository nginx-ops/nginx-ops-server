package io.github.nginx.ops.server.conf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.conf.domain.ConfInfoCertificate;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoCertificateDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoCertificateQuery;
import io.github.nginx.ops.server.conf.mapper.ConfInfoCertificateMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoCertificateService;
import io.github.nginx.ops.server.conf.service.ConfInfoServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_certificate(证书表)】的数据库操作Service实现
 * @createDate 2022-08-22 11:36:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoCertificateServiceImpl
    extends ServiceImpl<ConfInfoCertificateMapper, ConfInfoCertificate>
    implements ConfInfoCertificateService {

  private final ConfInfoServerService confInfoServerService;

  private final LambdaQueryWrapper<ConfInfoCertificate> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public void save(ConfInfoCertificateDTO dto) {
    ConfInfoCertificate confInfoCertificate =
        BeanUtil.copyProperties(dto, ConfInfoCertificate.class);
    this.save(confInfoCertificate);
  }

  @Override
  public void delete(String id) {
    if (confInfoServerService.selectCountByCertificateId(id) > 0) {
      throw new BusinessException("该证书已有绑定的反向代理, 请先删除和反向代理的关联关系后再删除证书!");
    }
    this.removeById(id);
  }

  @Override
  public void updateById(String id, ConfInfoCertificateDTO dto) {
    ConfInfoCertificate confInfoCertificate =
        BeanUtil.copyProperties(dto, ConfInfoCertificate.class);
    confInfoCertificate.setId(id);
    this.updateById(confInfoCertificate);
  }

  @Override
  public List<ConfInfoCertificate> list(ConfInfoCertificateQuery query) {
    queryWrapper.clear();
    queryWrapper.like(
        ObjectUtil.isNotEmpty(query.getDomain()),
        ConfInfoCertificate::getDomain,
        query.getDomain());
    return this.list(queryWrapper);
  }

  @Override
  public ConfInfoCertificateDTO getOne(String id) {
    ConfInfoCertificate confInfoCertificate = this.getById(id);
    ConfInfoCertificateDTO confInfoCertificateDTO =
        BeanUtil.copyProperties(confInfoCertificate, ConfInfoCertificateDTO.class);
    return confInfoCertificateDTO;
  }
}
