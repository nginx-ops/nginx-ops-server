package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.system.domain.SysRole;
import io.github.nginx.ops.server.system.domain.dto.SysRoleDTO;
import io.github.nginx.ops.server.system.domain.query.SysRoleQuery;
import io.github.nginx.ops.server.system.mapper.SysRoleMapper;
import io.github.nginx.ops.server.system.service.SysRoleService;
import io.github.nginx.ops.server.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author 24709
 * @description 针对表【sys_role(角色表)】的数据库操作Service实现
 * @createDate 2022-08-27 13:58:57
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService {

  private final LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
  private final SysUserRoleService sysUserRoleService;

  @Override
  public List<SysRoleDTO> selectSysRoleListByUserId(String id) {
    queryWrapper.clear();
    Set<String> roleIds = sysUserRoleService.selectRoleIdListByUserId(id);
    queryWrapper.in(SysRole::getRoleCode, roleIds).eq(SysRole::getIsEnable, true);
    List<SysRole> sysRoleList = this.list(queryWrapper);
    return BeanUtil.copyToList(sysRoleList, SysRoleDTO.class);
  }

  @Override
  public void save(SysRoleDTO dto) {
    queryWrapper.clear();
    queryWrapper.eq(SysRole::getRoleCode, dto.getRoleCode());
    if (this.count(queryWrapper) > 0) {
      throw new BusinessException("角色代码重复, 请核实后重新提交!");
    }
    SysRole sysRole = BeanUtil.copyProperties(dto, SysRole.class);
    this.save(sysRole);
  }

  @Override
  public void delete(String id) {
    this.removeById(id);
  }

  @Override
  public void update(String id, SysRoleDTO dto) {
    SysRole sysRole = this.getById(id);
    // 判断是否修改登录名
    if (ObjectUtil.isNotEmpty(sysRole)) {
      throw new BusinessException("无效的角色ID!");
    }
    // 判断是否修改登录名
    if (ObjectUtil.isNotEmpty(dto.getRoleCode())) {
      queryWrapper.clear();
      queryWrapper
          .eq(SysRole::getRoleCode, dto.getRoleCode())
          .ne(SysRole::getRoleCode, sysRole.getRoleCode());
      if (this.count(queryWrapper) > 0) {
        throw new BusinessException("角色代码重复, 请核实后重新提交!");
      }
    }
    SysRole updateSysRole = BeanUtil.copyProperties(dto, SysRole.class);
    updateSysRole.setId(id);
    this.updateById(updateSysRole);
  }

  @Override
  public List<SysRole> list(SysRoleQuery query) {
    queryWrapper.clear();
    queryWrapper
        .like(ObjectUtil.isNotEmpty(query.getRoleCode()), SysRole::getRoleCode, query.getRoleCode())
        .like(ObjectUtil.isNotEmpty(query.getRoleName()), SysRole::getRoleName, query.getRoleName())
        .like(ObjectUtil.isNotEmpty(query.getRemark()), SysRole::getRemark, query.getRemark())
        .eq(ObjectUtil.isNotEmpty(query.getIsEnable()), SysRole::getIsEnable, query.getIsEnable());
    return this.list(queryWrapper);
  }

  @Override
  public SysRoleDTO getOne(String id) {
    SysRole sysRole = this.getById(id);
    SysRoleDTO sysRoleDTO = BeanUtil.copyProperties(sysRole, SysRoleDTO.class);
    return sysRoleDTO;
  }
}
