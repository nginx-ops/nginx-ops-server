package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.exception.BusinessException;
import io.github.nginx.ops.server.system.domain.SysMenu;
import io.github.nginx.ops.server.system.domain.dto.SysMenuDTO;
import io.github.nginx.ops.server.system.domain.query.SysMenuQuery;
import io.github.nginx.ops.server.system.mapper.SysMenuMapper;
import io.github.nginx.ops.server.system.service.SysMenuService;
import io.github.nginx.ops.server.system.service.SysRoleMenuService;
import io.github.nginx.ops.server.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wgy
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2022-10-04 13:58:16
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService {

  private final LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();

  private final SysUserRoleService sysUserRoleService;
  private final SysRoleMenuService sysRoleMenuService;
  private final SysMenuMapper sysMenuMapper;

  @Override
  public Set<String> getPermsByUserId(String userId) {
    // 获取角色id列表
    List<String> roleIdList = sysUserRoleService.selectRoleIdListByUserId(userId);
    return sysMenuMapper.selectPermByRoleIds(roleIdList);
  }

  @Override
  public List<SysMenu> getRouterMenuByUserId(String userId) {
    // 获取角色id列表
    List<String> roleIdList = sysUserRoleService.selectRoleIdListByUserId(userId);
    // 获取角色id列表对应的菜单id列表
    List<String> menuIdList = sysRoleMenuService.getMenuIdsByRoleIds(roleIdList);
    queryWrapper.clear();
    queryWrapper
        .in(SysMenu::getMenuType, "C", "M")
        .in(!roleIdList.contains("1"), SysMenu::getId, menuIdList)
        .eq(!roleIdList.contains("1"), SysMenu::getIsEnable, "1")
        .orderByAsc(SysMenu::getOrder);
    // 获取路由需要的菜单
    List<SysMenu> routerMenuList = this.list(queryWrapper);
    // 递归构造属性路由菜单
    return routerMenuList.stream()
        .filter(
            parentMenu -> {
              return "0".equals(parentMenu.getParentId());
            })
        .peek(
            parentMenu -> {
              // 处理二级菜单
              parentMenu.setChildren(getChildrenList(parentMenu, routerMenuList));
            })
        .collect(Collectors.toList());
  }

  /**
   * 递归获取二级菜单
   *
   * @param sysParentMenu 父菜单
   * @param sysMenuList 系统菜单列表
   * @return @return {@link List }<{@link SysMenu }>
   * @author wgy
   * @data 2022/10/22
   */
  private List<SysMenu> getChildrenList(SysMenu sysParentMenu, List<SysMenu> sysMenuList) {
    return sysMenuList.stream()
        .filter(
            childrenMenu -> {
              return ObjectUtils.equals(sysParentMenu.getId(), childrenMenu.getParentId());
            })
        .peek(childrenMenu -> childrenMenu.setChildren(getChildrenList(childrenMenu, sysMenuList)))
        .sorted(
            (menu1, menu2) -> {
              // 排序
              return (menu1.getOrder() == null ? 0 : menu1.getOrder())
                  - (menu2.getOrder() == null ? 0 : menu2.getOrder());
            })
        .collect(Collectors.toList());
  }

  @Override
  public List<SysMenu> getMenuList(SysMenuQuery sysMenuQuery) {
    queryWrapper.clear();
    queryWrapper
        .like(
            StringUtils.isNotEmpty(sysMenuQuery.getMenuName()),
            SysMenu::getMenuName,
            sysMenuQuery.getMenuName())
        .eq(
            ObjectUtils.isNotEmpty(sysMenuQuery.getIsEnable()),
            SysMenu::getIsEnable,
            sysMenuQuery.getIsEnable())
        .orderByAsc(SysMenu::getOrder);
    return this.list(queryWrapper);
  }

  @Override
  public void update(SysMenuDTO dto) {
    SysMenu sysMenu = this.getById(dto.getId());
    SysMenu sysMenuParent = this.getById(dto.getParentId());
    // 判断id是否有效
    if (ObjectUtil.isEmpty(sysMenu)) {
      throw new BusinessException("无效的菜单ID!");
    }
    if (ObjectUtil.isEmpty(sysMenuParent)) {
      throw new BusinessException("无效的父菜单菜单ID!");
    }
    SysMenu sysMenuUpdate = BeanUtil.copyProperties(dto, SysMenu.class);
    this.updateById(sysMenuUpdate);
  }

  @Override
  public void delete(String id) {
    queryWrapper.clear();
    // 查询是否有次id
    SysMenu sysMenu = this.getById(id);
    if (ObjectUtil.isEmpty(sysMenu)) {
      throw new BusinessException("无效的菜单ID!");
    }
    // 查询是否有子菜单
    queryWrapper.eq(SysMenu::getParentId, id);
    if (this.count(queryWrapper) > 0 && "0".equals(sysMenu.getParentId())) {
      throw new BusinessException("该菜单仍有子菜单无法删除!");
    }
    // 删除关联表中的菜单关联
    sysRoleMenuService.deleteByMenuId(id);
    this.removeById(id);
  }

  @Override
  public void save(SysMenuDTO dto) {
    queryWrapper.clear();
    queryWrapper.eq(SysMenu::getMenuName, dto.getMenuName());
    if (this.count(queryWrapper) > 0) {
      throw new BusinessException("菜单名重复, 请核实后重新提交!");
    }
    SysMenu sysMenu = BeanUtil.copyProperties(dto, SysMenu.class);
    this.save(sysMenu);
  }
}
