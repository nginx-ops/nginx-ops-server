package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.domain.entity.BaseEntity;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.system.domain.SysRole;
import io.github.nginx.ops.server.system.domain.SysRoleMenu;
import io.github.nginx.ops.server.system.domain.dto.SysRoleDTO;
import io.github.nginx.ops.server.system.domain.query.SysRoleQuery;
import io.github.nginx.ops.server.system.mapper.SysRoleMapper;
import io.github.nginx.ops.server.system.service.SysRoleMenuService;
import io.github.nginx.ops.server.system.service.SysRoleService;
import io.github.nginx.ops.server.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wgy
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
  private final SysRoleMenuService sysRoleMenuService;

  @Override
  public List<SysRoleDTO> selectSysRoleListByUserId(String id) {
    queryWrapper.clear();
    List<String> roleIds = sysUserRoleService.selectRoleIdListByUserId(id);
    if (ObjectUtil.isEmpty(roleIds)) {
      return Collections.emptyList();
    }
    queryWrapper.in(BaseEntity::getId, roleIds);
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
    // 添加角色
    this.save(sysRole);
    // 存放角色对应菜单id
    ArrayList<SysRoleMenu> sysRoleMenus = new ArrayList<>();
    // 遍历填充连接对象
    if (dto.getUpdateMenuIdList() != null && dto.getUpdateMenuIdList().size() > 0) {
      for (String menuId : dto.getUpdateMenuIdList()) {
        SysRoleMenu sysRoleMenu = new SysRoleMenu(null, sysRole.getId(), menuId);
        sysRoleMenus.add(sysRoleMenu);
      }
    }
    // 添加链接表
    sysRoleMenuService.saveBatch(sysRoleMenus);
  }

  @Override
  public void delete(String id) {
    // 删除连接表
    sysRoleMenuService.deleteByRoleId(id);
    // 删除角色表
    this.removeById(id);
  }

  @Override
  public void update(SysRoleDTO dto) {
    SysRole sysRole = this.getById(dto.getId());
    // 判断是否修改登录名
    if (ObjectUtil.isEmpty(sysRole)) {
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
    this.updateById(updateSysRole);
    // 删除连接表
    sysRoleMenuService.deleteByRoleId(dto.getId());
    // 重新填充链接表
    List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
    // 遍历填充连接对象
    if (dto.getUpdateMenuIdList() != null && dto.getUpdateMenuIdList().size() > 0) {
      for (String menuId : dto.getUpdateMenuIdList()) {
        SysRoleMenu sysRoleMenu = new SysRoleMenu(null, sysRole.getId(), menuId);
        sysRoleMenus.add(sysRoleMenu);
      }
    }
    // 添加链接表
    sysRoleMenuService.saveBatch(sysRoleMenus);
  }

  @Override
  public List<SysRole> list(SysRoleQuery query) {
    queryWrapper.clear();
    queryWrapper
        .like(ObjectUtil.isNotEmpty(query.getRoleCode()), SysRole::getRoleCode, query.getRoleCode())
        .like(ObjectUtil.isNotEmpty(query.getRoleName()), SysRole::getRoleName, query.getRoleName())
        .ge(
            ObjectUtil.isNotEmpty(query.getBeginTime()),
            SysRole::getCreateTime,
            query.getBeginTime())
        .le(ObjectUtil.isNotEmpty(query.getEndTime()), SysRole::getCreateTime, query.getEndTime())
        .eq(ObjectUtil.isNotEmpty(query.getIsEnable()), SysRole::getIsEnable, query.getIsEnable());
    return this.list(queryWrapper);
  }

  @Override
  public SysRoleDTO getOne(String id) {
    SysRole sysRole = this.getById(id);
    if (ObjectUtil.isEmpty(sysRole)) {
      throw new BusinessException("无效的角色ID!");
    }
    SysRoleDTO sysRoleDTO = BeanUtil.copyProperties(sysRole, SysRoleDTO.class);
    // 填充一个角色列表进行查询
    ArrayList<String> roleList = new ArrayList<>();
    roleList.add(id);
    // 获取该角色选中菜单
    List<String> menuIdsByRoleId = sysRoleMenuService.getMenuIdsByRoleIds(roleList);
    // 装填集合
    sysRoleDTO.setSelectMenuIdList(menuIdsByRoleId);
    return sysRoleDTO;
  }
}
