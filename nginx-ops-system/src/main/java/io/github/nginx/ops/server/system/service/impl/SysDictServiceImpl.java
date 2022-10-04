package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.system.domain.SysDict;
import io.github.nginx.ops.server.system.domain.query.SysDictQuery;
import io.github.nginx.ops.server.system.domain.vo.SysDictCacheVO;
import io.github.nginx.ops.server.system.mapper.SysDictMapper;
import io.github.nginx.ops.server.system.service.SysDictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 24709
 * @description 针对表【sys_dict(系统设置-字典表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:50:43
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict>
    implements SysDictService {

  private final LambdaQueryWrapper<SysDict> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  public List<SysDict> list(SysDictQuery query) {
    queryWrapper.clear();
    queryWrapper
        .eq(ObjectUtil.isNotEmpty(query.getType()), SysDict::getType, query.getType())
        .eq(ObjectUtil.isNotEmpty(query.getCode()), SysDict::getCode, query.getCode())
        .like(ObjectUtil.isNotEmpty(query.getValue()), SysDict::getValue, query.getValue())
        .like(ObjectUtil.isNotEmpty(query.getRemark()), SysDict::getRemark, query.getRemark());
    return this.list(queryWrapper);
  }

  @Override
  public Map<String, List<SysDict>> getCacheMap() {
    List<SysDict> dictList = this.list();
     return dictList.stream().collect(Collectors.groupingBy(SysDict::getType, LinkedHashMap::new, Collectors.toList()));
  }
}
