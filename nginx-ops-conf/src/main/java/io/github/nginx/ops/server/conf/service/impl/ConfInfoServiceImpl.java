package io.github.nginx.ops.server.conf.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.odiszapc.nginxparser.NgxConfig;
import com.github.odiszapc.nginxparser.NgxDumper;
import com.github.odiszapc.nginxparser.NgxParam;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.conf.domain.ConfInfo;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoItemVO;
import io.github.nginx.ops.server.conf.domain.vo.ConfInfoVO;
import io.github.nginx.ops.server.conf.domain.vo.FileVo;
import io.github.nginx.ops.server.conf.enums.NginxConfTypeEnum;
import io.github.nginx.ops.server.conf.mapper.ConfInfoMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoCommService;
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
    String result = NginxConfUtils.test(sysSetting.getNginxExe());
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
  public String reload(ConfInfoVO confInfoVO) {
    SysSetting sysSetting = sysSettingService.getOneByLogin();
    String result = NginxConfUtils.reload(sysSetting.getNginxReloadCmd());
    if (!ObjectUtil.isEmpty(result) || !result.contains("signal process started")) {
      throw new BusinessException(result);
    }
    // 获取之前的配置文件
    queryWrapper.clear();
    ConfInfo confInfo = this.getOne(queryWrapper);

    return result;
  }

  @Override
  public String stop() {
    return null;
  }

  @Override
  public String run() {
    return null;
  }

  @Override
  public Boolean status() {
    return null;
  }
}
