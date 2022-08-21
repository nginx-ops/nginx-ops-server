package io.github.nginx.ops.server.conf.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.odiszapc.nginxparser.NgxBlock;
import com.github.odiszapc.nginxparser.NgxParam;
import io.github.nginx.ops.server.conf.domain.ConfInfoComm;
import io.github.nginx.ops.server.conf.domain.query.ConfInfoCommQuery;
import io.github.nginx.ops.server.conf.enums.NginxConfTypeEnum;
import io.github.nginx.ops.server.conf.mapper.ConfInfoCommMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoCommService;
import io.github.nginx.ops.server.conf.util.NginxConfUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 24709
 * @description 针对表【conf_info_comm(nginx通用配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoCommServiceImpl extends ServiceImpl<ConfInfoCommMapper, ConfInfoComm>
    implements ConfInfoCommService {

  private final LambdaQueryWrapper<ConfInfoComm> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public List<ConfInfoComm> list(ConfInfoCommQuery query) {
    queryWrapper.clear();
    queryWrapper
        .eq(ObjectUtil.isNotEmpty(query.getOtherId()), ConfInfoComm::getOtherId, query.getOtherId())
        .eq(ObjectUtil.isNotEmpty(query.getType()), ConfInfoComm::getType, query.getType())
        .like(ObjectUtil.isNotEmpty(query.getName()), ConfInfoComm::getName, query.getName())
        .like(ObjectUtil.isNotEmpty(query.getRemark()), ConfInfoComm::getRemark, query.getRemark());
    return this.list(queryWrapper);
  }

  @Override
  public void deleteByOtherId(String otherId) {
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoComm::getOtherId, otherId);
    this.remove(queryWrapper);
  }

  @Override
  public void deleteByOtherId(Set<String> ids) {
    queryWrapper.clear();
    queryWrapper.in(ConfInfoComm::getOtherId, ids);
    this.remove(queryWrapper);
  }

  @Override
  public List<ConfInfoComm> selectByOtherId(String otherId) {
    queryWrapper.clear();
    queryWrapper.eq(ConfInfoComm::getOtherId, otherId);
    return this.list(queryWrapper);
  }

  @Override
  public Map<String, List<ConfInfoComm>> getMap(Set<String> otherIds) {
    queryWrapper.clear();
    queryWrapper.in(ConfInfoComm::getOtherId, otherIds);
    List<ConfInfoComm> list = this.list(queryWrapper);
    return list.stream().collect(Collectors.groupingBy(ConfInfoComm::getOtherId));
  }

  @Override
  public NgxParam buildIndex() {
    NgxParam ngxParam = new NgxParam();
    List<ConfInfoComm> list =
        this.list(ConfInfoCommQuery.builder().type(NginxConfTypeEnum.INDEX.getCode()).build());
    list.forEach(
        item ->
            ngxParam.addValue(
                item.getName().trim() + NginxConfUtils.SPACE + item.getValue().trim()));
    return ngxParam;
  }

  @Override
  public NgxBlock buildHttp() {
    List<ConfInfoComm> list =
        this.list(ConfInfoCommQuery.builder().type(NginxConfTypeEnum.HTTP.getCode()).build());
    NgxBlock ngxBlockHttp = new NgxBlock();
    ngxBlockHttp.addValue(NginxConfTypeEnum.HTTP.getCode());
    list.forEach(
        item -> {
          NgxParam ngxParam = new NgxParam();
          ngxParam.addValue(item.getName().trim() + NginxConfUtils.SPACE + item.getValue().trim());
          ngxBlockHttp.addEntry(ngxParam);
        });
    return ngxBlockHttp;
  }

  @Override
  public NgxBlock buildStream() {
    List<ConfInfoComm> list =
        this.list(ConfInfoCommQuery.builder().type(NginxConfTypeEnum.STREAM.getCode()).build());
    NgxBlock ngxBlockStream = new NgxBlock();
    ngxBlockStream.addValue(NginxConfTypeEnum.STREAM.getCode());
    list.forEach(
        item -> {
          NgxParam ngxParam = new NgxParam();
          ngxParam.addValue(item.getName().trim() + NginxConfUtils.SPACE + item.getValue().trim());
          ngxBlockStream.addEntry(ngxParam);
        });
    return ngxBlockStream;
  }
}
