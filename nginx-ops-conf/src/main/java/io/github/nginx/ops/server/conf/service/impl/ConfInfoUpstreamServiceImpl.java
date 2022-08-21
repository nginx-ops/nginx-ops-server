package io.github.nginx.ops.server.conf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.odiszapc.nginxparser.NgxBlock;
import com.github.odiszapc.nginxparser.NgxConfig;
import com.github.odiszapc.nginxparser.NgxDumper;
import com.github.odiszapc.nginxparser.NgxParam;
import io.github.nginx.ops.server.conf.domain.ConfInfoComm;
import io.github.nginx.ops.server.conf.domain.ConfInfoUpstream;
import io.github.nginx.ops.server.conf.domain.ConfInfoUpstreamItem;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoCommDTO;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoUpstreamDTO;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoUpstreamItemDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoUpstreamQuery;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoItemVO;
import io.github.nginx.ops.server.conf.mapper.ConfInfoUpstreamMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoCommService;
import io.github.nginx.ops.server.conf.service.ConfInfoUpstreamItemService;
import io.github.nginx.ops.server.conf.service.ConfInfoUpstreamService;
import io.github.nginx.ops.server.conf.util.NginxConfUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info_upstream(nginx 负载均衡头表配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoUpstreamServiceImpl
    extends ServiceImpl<ConfInfoUpstreamMapper, ConfInfoUpstream>
    implements ConfInfoUpstreamService {

  private final ConfInfoCommService confInfoCommService;
  private final ConfInfoUpstreamItemService confInfoUpstreamItemService;

  private final LambdaQueryWrapper<ConfInfoUpstream> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(ConfInfoUpstreamDTO dto) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("插入头表");
    ConfInfoUpstream confInfoUpstream = BeanUtil.copyProperties(dto, ConfInfoUpstream.class);
    this.save(confInfoUpstream);
    stopWatch.stop();
    stopWatch.start("插入其他参数");
    if (ObjectUtil.isNotEmpty(dto.getConfInfoCommList())) {
      List<ConfInfoComm> confInfoCommList =
          BeanUtil.copyToList(dto.getConfInfoCommList(), ConfInfoComm.class);
      confInfoCommList.forEach(item -> item.setOtherId(confInfoUpstream.getId()));
      confInfoCommService.saveBatch(confInfoCommList);
    }
    stopWatch.stop();
    stopWatch.start("插入明细表");
    if (ObjectUtil.isNotEmpty(dto.getConfInfoUpstreamItemList())) {
      List<ConfInfoUpstreamItem> confInfoUpstreamItemList =
          BeanUtil.copyToList(dto.getConfInfoUpstreamItemList(), ConfInfoUpstreamItem.class);
      confInfoUpstreamItemList.forEach(item -> item.setUpstreamId(confInfoUpstream.getId()));
      confInfoUpstreamItemService.saveBatch(confInfoUpstreamItemList);
    }
    stopWatch.stop();
    log.debug("负载均衡配置表保存成功, 执行过程为:{}", stopWatch.prettyPrint());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(String id) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("删除头表");
    this.removeById(id);
    stopWatch.stop();
    stopWatch.start("删除其他参数");
    confInfoCommService.deleteByOtherId(id);
    stopWatch.stop();
    stopWatch.start("删除明细表");
    confInfoUpstreamItemService.deleteByUpstreamId(id);
    stopWatch.stop();
    log.debug("负载均衡配置表删除成功, 执行过程为:{}", stopWatch.prettyPrint());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateById(String id, ConfInfoUpstreamDTO dto) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("修改头表");
    ConfInfoUpstream confInfoUpstream = BeanUtil.copyProperties(dto, ConfInfoUpstream.class);
    confInfoUpstream.setId(id);
    this.updateById(confInfoUpstream);
    stopWatch.stop();
    stopWatch.start("修改其他参数");
    confInfoCommService.deleteByOtherId(id);
    if (ObjectUtil.isNotEmpty(dto.getConfInfoCommList())) {
      List<ConfInfoComm> confInfoCommList =
          BeanUtil.copyToList(dto.getConfInfoCommList(), ConfInfoComm.class);
      confInfoCommList.forEach(item -> item.setOtherId(confInfoUpstream.getId()));
      confInfoCommService.saveBatch(confInfoCommList);
    }
    stopWatch.stop();
    stopWatch.start("修改明细表");
    confInfoUpstreamItemService.deleteByUpstreamId(id);
    if (ObjectUtil.isNotEmpty(dto.getConfInfoUpstreamItemList())) {
      List<ConfInfoUpstreamItem> confInfoUpstreamItemList =
          BeanUtil.copyToList(dto.getConfInfoUpstreamItemList(), ConfInfoUpstreamItem.class);
      confInfoUpstreamItemList.forEach(item -> item.setUpstreamId(confInfoUpstream.getId()));
      confInfoUpstreamItemService.saveBatch(confInfoUpstreamItemList);
    }
    stopWatch.stop();
    log.debug("负载均衡配置表修改成功, 执行过程为:{}", stopWatch.prettyPrint());
  }

  @Override
  public List<ConfInfoUpstream> list(ConfInfoUpstreamQuery query) {
    queryWrapper.clear();
    queryWrapper
        .like(ObjectUtil.isNotEmpty(query.getName()), ConfInfoUpstream::getName, query.getName())
        .like(
            ObjectUtil.isNotEmpty(query.getRemark()),
            ConfInfoUpstream::getRemark,
            query.getRemark())
        .eq(
            ObjectUtil.isNotEmpty(query.getTactics()),
            ConfInfoUpstream::getTactics,
            query.getTactics())
        .eq(
            ObjectUtil.isNotEmpty(query.getProxyType()),
            ConfInfoUpstream::getProxyType,
            query.getProxyType());
    return this.list(queryWrapper);
  }

  @Override
  public ConfInfoUpstreamDTO getOne(String id) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("查询表头");
    ConfInfoUpstream confInfoUpstream = this.getById(id);
    stopWatch.stop();
    stopWatch.start("查询其他参数");
    List<ConfInfoComm> confInfoCommList = confInfoCommService.selectByOtherId(id);
    stopWatch.stop();
    stopWatch.start("查询明细表");
    List<ConfInfoUpstreamItem> confInfoUpstreamItemList =
        confInfoUpstreamItemService.selectByUpstreamId(id);
    stopWatch.stop();
    stopWatch.start("装配返回");
    ConfInfoUpstreamDTO confInfoUpstreamDTO =
        BeanUtil.copyProperties(confInfoUpstream, ConfInfoUpstreamDTO.class);
    confInfoUpstreamDTO.setConfInfoCommList(
        BeanUtil.copyToList(confInfoCommList, ConfInfoCommDTO.class));
    confInfoUpstreamDTO.setConfInfoUpstreamItemList(
        BeanUtil.copyToList(confInfoUpstreamItemList, ConfInfoUpstreamItemDTO.class));
    stopWatch.stop();
    return confInfoUpstreamDTO;
  }

  @Override
  public NgxBlock buildBlockUpstream(String id) {
    ConfInfoUpstreamDTO confInfoUpstreamDTO = this.getOne(id);
    return this.buildBlockUpstream(confInfoUpstreamDTO);
  }

  @Override
  public NgxBlock buildBlockUpstream(ConfInfoUpstreamDTO dto) {
    NgxBlock ngxBlockServer = new NgxBlock();
    NgxParam ngxParam = null;
    // 拼接名称
    ngxBlockServer.addValue("upstream " + dto.getName());
    // 装配负载模式
    if (ObjectUtil.isNotEmpty(dto.getTactics())) {
      ngxParam = new NgxParam();
      ngxParam.addValue(dto.getTactics());
      ngxBlockServer.addEntry(ngxParam);
    }
    // 装配服务
    if (ObjectUtil.isNotEmpty(dto.getConfInfoUpstreamItemList())) {
      for (ConfInfoUpstreamItemDTO item : dto.getConfInfoUpstreamItemList()) {
        ngxParam = new NgxParam();
        ngxParam.addValue("server " + this.buildNodeStr(item));
        ngxBlockServer.addEntry(ngxParam);
      }
    }
    return ngxBlockServer;
  }

  @Override
  public List<ConfInfoItemVO> createTempFile() {
    List<ConfInfoUpstream> list = this.list();
    if (ObjectUtil.isNotEmpty(list)) {
      return Collections.emptyList();
    }
    NgxConfig ngxConfig = new NgxConfig();
    List<ConfInfoItemVO> confInfoItemVOList = new ArrayList<>();
    list.forEach(
        item -> {
          NgxBlock ngxBlockUpstream = this.buildBlockUpstream(item.getId());
          ngxConfig.addEntry(ngxBlockUpstream);
          String serverConf = new NgxDumper(ngxConfig).dump();
          String tempConfFile = NginxConfUtils.createTempConfFile(serverConf, item.getName());
          confInfoItemVOList.add(
              ConfInfoItemVO.builder()
                  .name(FileNameUtil.getName(tempConfFile))
                  .path(tempConfFile)
                  .content(serverConf)
                  .build());
        });
    return confInfoItemVOList;
  }

  private String buildNodeStr(ConfInfoUpstreamItemDTO upstreamServer) {
    StringBuilder conf = new StringBuilder();
    conf.append(upstreamServer.getIp()).append(":").append(upstreamServer.getPort());
    if (upstreamServer.getWeight() != null) {
      conf.append(" weight=").append(upstreamServer.getWeight());
    }
    if (upstreamServer.getFailTimeout() != null) {
      conf.append(" fail_timeout=").append(upstreamServer.getFailTimeout()).append("s");
    }
    if (upstreamServer.getMaxFails() != null) {
      conf.append(" max_fails=").append(upstreamServer.getMaxFails());
    }
    if (upstreamServer.getMaxConns() != null) {
      conf.append(" max_conns=").append(upstreamServer.getMaxConns());
    }
    if (!"none".equals(upstreamServer.getStatus())) {
      conf.append(" ").append(upstreamServer.getStatus());
    }
    return conf.toString();
  }
}
