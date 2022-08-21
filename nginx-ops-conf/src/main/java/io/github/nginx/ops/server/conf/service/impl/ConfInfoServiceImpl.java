package io.github.nginx.ops.server.conf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RuntimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.odiszapc.nginxparser.NgxConfig;
import com.github.odiszapc.nginxparser.NgxDumper;
import com.github.odiszapc.nginxparser.NgxParam;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.conf.domain.ConfInfo;
import io.github.nginx.ops.server.conf.domain.ConfInfoHis;
import io.github.nginx.ops.server.conf.domain.ConfInfoHisItem;
import io.github.nginx.ops.server.conf.domain.ConfInfoItem;
import io.github.nginx.ops.server.conf.domain.dto.ReloadDTO;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoItemVO;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoVO;
import io.github.nginx.ops.server.conf.domain.vo.FileVo;
import io.github.nginx.ops.server.conf.enums.NginxConfTypeEnum;
import io.github.nginx.ops.server.conf.mapper.ConfInfoMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoCommService;
import io.github.nginx.ops.server.conf.service.ConfInfoHisItemService;
import io.github.nginx.ops.server.conf.service.ConfInfoHisService;
import io.github.nginx.ops.server.conf.service.ConfInfoItemService;
import io.github.nginx.ops.server.conf.service.ConfInfoServerService;
import io.github.nginx.ops.server.conf.service.ConfInfoService;
import io.github.nginx.ops.server.conf.service.ConfInfoUpstreamService;
import io.github.nginx.ops.server.conf.util.NginxConfUtils;
import io.github.nginx.ops.server.system.domain.SysSetting;
import io.github.nginx.ops.server.system.service.SysSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 24709
 * @description 针对表【conf_info(nginx配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoServiceImpl extends ServiceImpl<ConfInfoMapper, ConfInfo>
    implements ConfInfoService {

  private final ConfInfoCommService confInfoCommService;
  private final ConfInfoItemService confInfoItemService;
  private final ConfInfoServerService confInfoServerService;
  private final ConfInfoUpstreamService confInfoUpstreamService;
  private final ConfInfoHisService confInfoHisService;
  private final ConfInfoHisItemService confInfoHisItemService;
  private final SysSettingService sysSettingService;

  private final LambdaQueryWrapper<ConfInfo> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public List<FileVo> nodeList(String pid) {
    File[] fileList = null;
    if (ObjectUtil.isEmpty(pid)) {
      fileList = File.listRoots();
    } else {
      fileList = new File(pid).listFiles();
    }
    List<FileVo> fileVoList = new ArrayList<>();
    if (ObjectUtil.isNotEmpty(fileList)) {
      for (File file : fileList) {
        fileVoList.add(
            FileVo.builder()
                .id(file.getPath())
                .pid(file.getParent())
                .isParent(file.isDirectory())
                .name(file.getName())
                .build());
      }
    }
    return fileVoList;
  }

  @Override
  public ConfInfoVO preview(String type, String id) {
    SysSetting sysSetting = sysSettingService.getOneByLogin();
    NgxConfig ngxConfig = new NgxConfig();
    ConfInfoVO confInfoVO = new ConfInfoVO();
    if (type.equals(NginxConfTypeEnum.INDEX.getCode())) {
      ngxConfig.addEntry(confInfoCommService.buildIndex());
    } else if (type.equals(NginxConfTypeEnum.HTTP.getCode())) {
      ngxConfig.addEntry(confInfoCommService.buildHttp());
    } else if (type.equals(NginxConfTypeEnum.STREAM.getCode())) {
      ngxConfig.addEntry(confInfoCommService.buildStream());
    } else if (type.equals(NginxConfTypeEnum.SERVER.getCode())) {
      ngxConfig.addEntry(confInfoServerService.buildBlockServer(id));
    } else if (type.equals(NginxConfTypeEnum.UPSTREAM.getCode())) {
      ngxConfig.addEntry(confInfoUpstreamService.buildBlockUpstream(id));
    } else if (ObjectUtil.isEmpty(type)) {
      // 生成临时文件
      ngxConfig.addEntry(confInfoCommService.buildIndex());
      ngxConfig.addEntry(confInfoCommService.buildHttp());
      ngxConfig.addEntry(confInfoCommService.buildStream());
      // 判断是否需要分割文件
      if (Boolean.TRUE.equals(sysSetting.getIsSplit())) {
        List<ConfInfoItemVO> confInfoItemVOList = new ArrayList<>();
        confInfoItemVOList.addAll(confInfoServerService.createTempFile());
        confInfoItemVOList.addAll(confInfoUpstreamService.createTempFile());
        if (ObjectUtil.isNotEmpty(confInfoItemVOList)) {
          NgxParam ngxParam = new NgxParam();
          confInfoItemVOList.forEach(
              item -> ngxParam.addValue("include" + NginxConfUtils.SPACE + item.getName()));
          ngxConfig.addEntry(ngxParam);
          confInfoVO.setConfInfoItemList(confInfoItemVOList);
        }
      } else {
        ngxConfig.addEntry(confInfoServerService.buildBlockServer(id));
        ngxConfig.addEntry(confInfoUpstreamService.buildBlockUpstream(id));
      }
    }
    // 生成配置
    String nginxConf = new NgxDumper(ngxConfig).dump();
    if (ObjectUtil.isEmpty(type)) {
      // 生成临时文件
      NginxConfUtils.createTempConfFile(nginxConf, "nginx");
    }
    confInfoVO.setContent(nginxConf);
    return confInfoVO;
  }

  @Override
  public String test() {
    SysSetting sysSetting = sysSettingService.getOneByLogin();
    String result =
        RuntimeUtil.execForStr(
            sysSetting.getNginxExe()
                + NginxConfUtils.SPACE
                + "-t -c"
                + NginxConfUtils.SPACE
                + NginxConfUtils.TMP_NGINX_INDEX_CONF_PATH);
    if (!result.contains("successful")) {
      throw new BusinessException(result);
    }
    return result;
  }

  @Override
  public void replace() {
    SysSetting sysSetting = sysSettingService.getOneByLogin();
    String nginxConfPath = sysSetting.getNginxConfPath();
    // 先copy到back中
    FileUtil.clean(NginxConfUtils.BACK_NGINX_CONF_PATH);
    FileUtil.copy(nginxConfPath, NginxConfUtils.BACK_NGINX_CONF_PATH, true);
    // 再删除
    FileUtil.clean(sysSetting.getNginxConfPath());
    // 在插入
    FileUtil.copy(NginxConfUtils.TMP_NGINX_CONF_PATH, sysSetting.getNginxConfPath(), true);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public String reload(ReloadDTO reloadDTO) {
    // 删除老配置文件
    this.removeById(reloadDTO.getOldConfInfo().getId());
    confInfoItemService.removeByConfId(reloadDTO.getOldConfInfo().getId());
    // 插入历史表
    confInfoHisService.save(BeanUtil.copyProperties(reloadDTO.getOldConfInfo(), ConfInfoHis.class));
    confInfoHisItemService.saveBatch(
        BeanUtil.copyToList(
            reloadDTO.getOldConfInfo().getConfInfoItemList(), ConfInfoHisItem.class));
    // 插入新配置文件
    this.save(BeanUtil.copyProperties(reloadDTO.getNewConfInfo(), ConfInfo.class));
    confInfoItemService.saveBatch(
        BeanUtil.copyToList(reloadDTO.getNewConfInfo().getConfInfoItemList(), ConfInfoItem.class));
    // 获取命令
    SysSetting sysSetting = sysSettingService.getOneByLogin();
    // 执行重启命令
    String result = RuntimeUtil.execForStr(sysSetting.getNginxReloadCmd());
    if (!ObjectUtil.isEmpty(result) || !result.contains("signal process started")) {
      throw new BusinessException(result);
    }
    return result;
  }

  @Override
  public String stop() {
    SysSetting sysSetting = sysSettingService.getOneByLogin();
    String result = RuntimeUtil.execForStr(sysSetting.getNginxStopCmd());
    if (this.status()) {
      throw new BusinessException("暂停失败, nginx返回结果为: " + result);
    }
    return result;
  }

  @Override
  public String run() {
    SysSetting sysSetting = sysSettingService.getOneByLogin();
    String result = RuntimeUtil.execForStr(sysSetting.getNginxStartCmd());
    if (!this.status()) {
      throw new BusinessException("启动失败, nginx返回结果为: " + result);
    }
    return result;
  }

  @Override
  public Boolean status() {
    boolean status;
    if (FileUtil.isWindows()) {
      String[] command = {"tasklist"};
      String rs = RuntimeUtil.execForStr(command);
      status = rs.toLowerCase().contains("nginx.exe");
    } else {
      String[] command = {"/bin/sh", "-c", "ps -ef | grep nginx"};
      String rs = RuntimeUtil.execForStr(command);
      status = rs.contains("nginx: master process") || rs.contains("nginx: worker process");
    }
    return status;
  }

  @Override
  public ConfInfoVO get() {
    queryWrapper.clear();
    ConfInfo confInfo = this.getOne(queryWrapper);
    List<ConfInfoItem> confInfoItemList = confInfoItemService.selectByConfId(confInfo.getId());
    ConfInfoVO confInfoVO = BeanUtil.copyProperties(confInfo, ConfInfoVO.class);
    confInfoVO.setConfInfoItemList(BeanUtil.copyToList(confInfoItemList, ConfInfoItemVO.class));
    return confInfoVO;
  }
}
