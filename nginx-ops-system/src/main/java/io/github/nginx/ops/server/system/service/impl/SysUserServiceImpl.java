package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.system.domain.SysUser;
import io.github.nginx.ops.server.system.domain.dto.SysUserDTO;
import io.github.nginx.ops.server.system.mapper.SysUserMapper;
import io.github.nginx.ops.server.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lihao3
 * @date 2022/8/24 9:41
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService {

  private final LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(SysUserDTO dto) {
    queryWrapper.clear();
    queryWrapper.eq(SysUser::getLoginName, dto.getLoginName());
    if (this.count(queryWrapper) > 0) {
      throw new BusinessException("登录名重复, 请核实后重新提交!");
    }
    SysUser sysUser = BeanUtil.copyProperties(dto, SysUser.class);
    this.save(sysUser);
  }

  @Override
  public void delete(String id) {}

  @Override
  public void update(String id, SysUserDTO dto) {}
}
