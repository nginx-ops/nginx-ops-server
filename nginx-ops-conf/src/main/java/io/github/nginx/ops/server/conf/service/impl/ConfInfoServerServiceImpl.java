package io.github.nginx.ops.server.conf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.odiszapc.nginxparser.NgxBlock;
import com.github.odiszapc.nginxparser.NgxConfig;
import com.github.odiszapc.nginxparser.NgxDumper;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import io.github.nginx.ops.server.conf.domain.ConfInfoComm;
import io.github.nginx.ops.server.conf.domain.ConfInfoServer;
import io.github.nginx.ops.server.conf.domain.ConfInfoServerItem;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoCommDTO;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoServerDTO;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoServerItemDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoServerQuery;
import io.github.nginx.ops.server.conf.mapper.ConfInfoServerMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoCommService;
import io.github.nginx.ops.server.conf.service.ConfInfoServerItemService;
import io.github.nginx.ops.server.conf.service.ConfInfoServerService;
import io.github.nginx.ops.server.conf.util.NginxConfUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 24709
 * @description 针对表【conf_info_server(nginx服务配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoServerServiceImpl extends ServiceImpl<ConfInfoServerMapper, ConfInfoServer>
    implements ConfInfoServerService {

  private final ConfInfoCommService confInfoCommService;
  private final ConfInfoServerItemService confInfoServerItemService;

  private final LambdaQueryWrapper<ConfInfoServer> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(ConfInfoServerDTO dto) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("插入头表");
    ConfInfoServer confInfoServer = BeanUtil.copyProperties(dto, ConfInfoServer.class);
    this.save(confInfoServer);
    stopWatch.stop();
    if (ObjectUtil.isNotEmpty(dto.getConfInfoCommList())) {
      stopWatch.start("插入其他参数");
      List<ConfInfoComm> confInfoCommList =
          BeanUtil.copyToList(dto.getConfInfoCommList(), ConfInfoComm.class);
      confInfoCommList.forEach(item -> item.setOtherId(confInfoServer.getId()));
      confInfoCommService.saveBatch(confInfoCommList);
      stopWatch.stop();
    }
    if (ObjectUtil.isNotEmpty(dto.getConfInfoServerItemList())) {
      List<ConfInfoComm> confInfoServerCommList = new ArrayList<>();
      stopWatch.start("插入明细表");
      dto.getConfInfoServerItemList()
          .forEach(
              item -> {
                // 插入明细头表
                ConfInfoServerItem confInfoServerItem =
                    BeanUtil.copyProperties(
                        dto.getConfInfoServerItemList(), ConfInfoServerItem.class);
                confInfoServerItem.setServerId(confInfoServer.getId());
                confInfoServerItemService.save(confInfoServerItem);
                if (ObjectUtil.isNotEmpty(item.getConfInfoCommList())) {
                  // 插入明细表其他参数
                  List<ConfInfoComm> confInfoCommList =
                      BeanUtil.copyToList(item.getConfInfoCommList(), ConfInfoComm.class);
                  confInfoCommList.forEach(
                      confInfoComm -> confInfoComm.setOtherId(confInfoServerItem.getId()));
                  confInfoServerCommList.addAll(confInfoCommList);
                }
              });
      stopWatch.stop();
      stopWatch.start("插入明细表其他参数");
      confInfoCommService.saveBatch(confInfoServerCommList);
      stopWatch.stop();
    }
    log.debug("反向代理保存成功, 执行过程为:{}", stopWatch.prettyPrint());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(String id) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("删除头表");
    this.removeById(id);
    stopWatch.stop();
    stopWatch.start("删除头表其他参数");
    confInfoCommService.deleteByOtherId(id);
    stopWatch.stop();
    stopWatch.start("删除明细表");
    confInfoServerItemService.deleteByServerId(id);
    stopWatch.stop();
    log.debug("反向代理删除成功, 执行过程为:{}", stopWatch.prettyPrint());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateById(String id, ConfInfoServerDTO dto) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("修改头表");
    ConfInfoServer confInfoServer = BeanUtil.copyProperties(dto, ConfInfoServer.class);
    confInfoServer.setId(id);
    this.updateById(confInfoServer);
    stopWatch.stop();
    stopWatch.start("修改其他参数");
    confInfoCommService.deleteByOtherId(id);
    if (ObjectUtil.isNotEmpty(dto.getConfInfoCommList())) {
      List<ConfInfoComm> confInfoCommList =
          BeanUtil.copyToList(dto.getConfInfoCommList(), ConfInfoComm.class);
      confInfoCommList.forEach(item -> item.setOtherId(confInfoServer.getId()));
      confInfoCommService.saveBatch(confInfoCommList);
    }
    stopWatch.stop();
    stopWatch.start("修改明细表");
    confInfoServerItemService.deleteByServerId(id);
    List<ConfInfoComm> confInfoServerCommList = new ArrayList<>();
    if (ObjectUtil.isNotEmpty(dto.getConfInfoServerItemList())) {
      dto.getConfInfoServerItemList()
          .forEach(
              item -> {
                // 插入明细头表
                ConfInfoServerItem confInfoServerItem =
                    BeanUtil.copyProperties(
                        dto.getConfInfoServerItemList(), ConfInfoServerItem.class);
                confInfoServerItem.setServerId(confInfoServer.getId());
                confInfoServerItemService.save(confInfoServerItem);
                if (ObjectUtil.isNotEmpty(item.getConfInfoCommList())) {
                  // 插入明细表其他参数
                  List<ConfInfoComm> confInfoCommList =
                      BeanUtil.copyToList(item.getConfInfoCommList(), ConfInfoComm.class);
                  confInfoCommList.forEach(
                      confInfoComm -> confInfoComm.setOtherId(confInfoServerItem.getId()));
                  confInfoServerCommList.addAll(confInfoCommList);
                }
              });
    }
    stopWatch.stop();
    stopWatch.start("修改明细表其他参数");
    confInfoCommService.saveBatch(confInfoServerCommList);
    stopWatch.stop();
    log.debug("反向代理修改成功, 执行过程为:{}", stopWatch.prettyPrint());
  }

  @Override
  public List<ConfInfoServer> list(ConfInfoServerQuery query) {
    queryWrapper.clear();
    queryWrapper
        .like(
            ObjectUtil.isNotEmpty(query.getServerName()),
            ConfInfoServer::getServerName,
            query.getServerName())
        .like(ObjectUtil.isNotEmpty(query.getIp()), ConfInfoServer::getIp, query.getIp())
        .like(ObjectUtil.isNotEmpty(query.getPort()), ConfInfoServer::getPort, query.getPort())
        .eq(ObjectUtil.isNotEmpty(query.getType()), ConfInfoServer::getType, query.getType())
        .eq(
            ObjectUtil.isNotEmpty(query.getCertificateId()),
            ConfInfoServer::getCertificateId,
            query.getCertificateId());
    return this.list(queryWrapper);
  }

  @Override
  public ConfInfoServerDTO getOne(String id) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("查询头表");
    ConfInfoServer confInfoServer = this.getById(id);
    stopWatch.stop();
    stopWatch.start("查询头表其他参数");
    List<ConfInfoComm> confInfoCommList = confInfoCommService.selectByOtherId(id);
    stopWatch.stop();
    stopWatch.start("查询明细表");
    List<ConfInfoServerItem> confInfoServerItemList =
        confInfoServerItemService.selectByServerId(id);
    Set<String> serverItemIds =
        confInfoServerItemList.stream().map(BaseEntity::getId).collect(Collectors.toSet());
    stopWatch.stop();
    stopWatch.start("查询明细表其他参数");
    Map<String, List<ConfInfoComm>> confInfoCommServiceItemMap =
        confInfoCommService.getMap(serverItemIds);
    stopWatch.stop();
    stopWatch.start("装配返回");
    ConfInfoServerDTO confInfoServerDTO =
        BeanUtil.copyProperties(confInfoServer, ConfInfoServerDTO.class);
    List<ConfInfoCommDTO> confInfoCommDTOList =
        BeanUtil.copyToList(confInfoCommList, ConfInfoCommDTO.class);
    List<ConfInfoServerItemDTO> confInfoServerItemDTOList = new ArrayList<>();
    confInfoServerItemList.forEach(
        item -> {
          ConfInfoServerItemDTO confInfoServerItemDTO =
              BeanUtil.copyProperties(item, ConfInfoServerItemDTO.class);
          if (confInfoCommServiceItemMap.containsKey(item.getId())) {
            List<ConfInfoComm> confInfoServerItemCommList =
                confInfoCommServiceItemMap.get(item.getId());
            confInfoServerItemDTO.setConfInfoCommList(
                BeanUtil.copyToList(confInfoServerItemCommList, ConfInfoCommDTO.class));
            confInfoServerItemDTOList.add(confInfoServerItemDTO);
          }
        });
    confInfoServerDTO.setConfInfoCommList(confInfoCommDTOList);
    confInfoServerDTO.setConfInfoServerItemList(confInfoServerItemDTOList);
    stopWatch.stop();
    log.debug("查询单条信息完成, 执行过程为:{}", stopWatch.prettyPrint());
    return confInfoServerDTO;
  }

  @Override
  public String preview(String id) {
    ConfInfoServerDTO confInfoServerDTO = this.getOne(id);
    NgxConfig ngxConfig = new NgxConfig();
    NgxBlock ngxBlock = NginxConfUtils.buildBlockServer(confInfoServerDTO);
    ngxConfig.addEntry(ngxBlock);
    return new NgxDumper(ngxConfig).dump();
  }
}
