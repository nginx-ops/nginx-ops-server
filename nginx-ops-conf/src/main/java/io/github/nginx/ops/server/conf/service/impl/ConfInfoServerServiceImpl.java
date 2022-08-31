package io.github.nginx.ops.server.conf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.odiszapc.nginxparser.NgxBlock;
import com.github.odiszapc.nginxparser.NgxConfig;
import com.github.odiszapc.nginxparser.NgxDumper;
import com.github.odiszapc.nginxparser.NgxParam;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import io.github.nginx.ops.server.conf.domain.ConfInfoComm;
import io.github.nginx.ops.server.conf.domain.ConfInfoServer;
import io.github.nginx.ops.server.conf.domain.ConfInfoServerItem;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoCommDTO;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoServerDTO;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoServerItemDTO;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoServerQuery;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoItemVO;
import io.github.nginx.ops.server.conf.mapper.ConfInfoServerMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoCommService;
import io.github.nginx.ops.server.conf.service.ConfInfoServerItemService;
import io.github.nginx.ops.server.conf.service.ConfInfoServerService;
import io.github.nginx.ops.server.conf.util.NginxConfUtils;
import io.github.nginx.ops.server.system.service.SysSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
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

  private final SysSettingService sysSettingService;
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

  /**
   * 构建其他配置文件
   *
   * @param dto
   * @param ngxBlock
   */
  private static void setSameParam(ConfInfoCommDTO dto, NgxBlock ngxBlock) {
    NgxParam ngxParam = new NgxParam();
    ngxParam.addValue(dto.getName().trim() + NginxConfUtils.SPACE + dto.getValue().trim());
    ngxBlock.addEntry(ngxParam);
  }

  @Override
  public NgxBlock buildBlockServer(String id) {
    ConfInfoServerDTO confInfoServerDTO = this.getOne(id);
    NgxBlock ngxBlockServer = new NgxBlock();
    ngxBlockServer.addValue("server");
    NgxParam ngxParam = null;
    // 监听域名
    ngxParam = new NgxParam();
    ngxParam.addValue("server_name" + NginxConfUtils.SPACE + confInfoServerDTO.getServerName());
    // 监听端口
    ngxParam = new NgxParam();
    String value = "listen" + NginxConfUtils.SPACE + confInfoServerDTO.getPort();
    if (confInfoServerDTO.getIsSsl() != null && confInfoServerDTO.getIsSsl()) {
      value += " ssl";
    }
    if (confInfoServerDTO.getIsHttp2() != null && confInfoServerDTO.getIsHttp2()) {
      value += " http2";
    }
    ngxParam.addValue(value);
    ngxBlockServer.addEntry(ngxParam);
    // https添加80端口重写
    if (Boolean.TRUE.equals(confInfoServerDTO.getHttpToHttps())) {
      NgxBlock ngxBlock = new NgxBlock();
      ngxBlock.addValue("if ($scheme = http)");
      ngxParam = new NgxParam();
      ngxParam.addValue(
          "return 301 https://$host:" + confInfoServerDTO.getHttpPort() + "$request_uri");
      ngxBlock.addEntry(ngxParam);
      ngxBlockServer.addEntry(ngxBlock);
    }
    // 额外参数
    if (ObjectUtil.isNotEmpty(confInfoServerDTO.getConfInfoCommList())) {
      confInfoServerDTO.getConfInfoCommList().forEach(item -> setSameParam(item, ngxBlockServer));
    }
    // location参数配置
    if (ObjectUtil.isNotEmpty(confInfoServerDTO.getConfInfoServerItemList())) {
      for (ConfInfoServerItemDTO item : confInfoServerDTO.getConfInfoServerItemList()) {
        NgxBlock ngxBlockLocation = new NgxBlock();
        ngxBlockLocation.addValue("location");
        ngxBlockLocation.addValue(item.getPath());
        // 设置header
        if (item.getIsHeader()) {
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header Host $host");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header X-Real-IP $remote_addr");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header X-Forwarded-Host $http_host");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header X-Forwarded-Port $server_port");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header X-Forwarded-Proto $scheme");
          ngxBlockLocation.addEntry(ngxParam);
        }
        // 设置Websocket
        if (item.getIsWebsocket()) {
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_http_version 1.1");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header Upgrade $http_upgrade");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header Connection \"upgrade\"");
          ngxBlockLocation.addEntry(ngxParam);
        }
        // redirect http转https
        if (confInfoServerDTO.getIsSsl()) {
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_redirect http:// https://");
          ngxBlockLocation.addEntry(ngxParam);
        }
        ngxBlockServer.addEntry(ngxBlockLocation);
      }
    }
    return ngxBlockServer;
  }

  @Override
  public List<ConfInfoItemVO> createTempFile() {
    List<ConfInfoServer> list = this.list();
    if (ObjectUtil.isNotEmpty(list)) {
      return Collections.emptyList();
    }
    NgxConfig ngxConfig = new NgxConfig();
    List<ConfInfoItemVO> confInfoItemVOList = new ArrayList<>();
    list.forEach(
        item -> {
          NgxBlock buildBlockServer = this.buildBlockServer(item.getId());
          ngxConfig.addEntry(buildBlockServer);
          String serverConf = new NgxDumper(ngxConfig).dump();
          String tempConfFile = NginxConfUtils.createTempConfFile(serverConf, item.getServerName());
          confInfoItemVOList.add(
              ConfInfoItemVO.builder()
                  .name(FileNameUtil.getName(tempConfFile))
                  .path(tempConfFile)
                  .content(serverConf)
                  .build());
        });
    return confInfoItemVOList;
  }

  @Override
  public long selectCountByCertificateId(String certificateId) {
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoServer::getCertificateId, certificateId);
    return this.count(queryWrapper);
  }

  @Override
  public List<ConfInfoItemVO> preview(String nginxConfPath) {
    List<ConfInfoServer> list = this.list();
    if (ObjectUtil.isNotEmpty(list)) {
      return Collections.emptyList();
    }
    NgxConfig ngxConfig = new NgxConfig();
    List<ConfInfoItemVO> confInfoItemVOList = new ArrayList<>();
    list.forEach(
        item -> {
          NgxBlock buildBlockServer = this.buildBlockServer(item.getId());
          ngxConfig.addEntry(buildBlockServer);
          String serverConf = new NgxDumper(ngxConfig).dump();
          String fileName = item.getServerName() + ".conf";
          confInfoItemVOList.add(
              ConfInfoItemVO.builder()
                  .name(fileName)
                  .path(
                      FileUtil.normalize(nginxConfPath + NginxConfUtils.FILE_SEPARATOR + fileName))
                  .content(serverConf)
                  .build());
        });
    return confInfoItemVOList;
  }
}
