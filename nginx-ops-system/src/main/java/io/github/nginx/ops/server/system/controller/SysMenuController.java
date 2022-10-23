package io.github.nginx.ops.server.system.controller;

import io.github.nginx.ops.server.comm.annotation.OperationLog;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.enums.BusinessTypeEnum;
import io.github.nginx.ops.server.system.domain.SysMenu;
import io.github.nginx.ops.server.system.domain.dto.SysMenuDTO;
import io.github.nginx.ops.server.system.domain.query.SysMenuQuery;
import io.github.nginx.ops.server.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wgy @CreateTime: 2022-10-05 19:31:13 @Description:
 */
@Api(tags = "菜单管理接口")
@Slf4j
@RestController
@RequestMapping("sys/menu")
@RequiredArgsConstructor
public class SysMenuController {

  private final SysMenuService sysMenuService;

  @PostMapping
  @ApiOperation("新增菜单")
  @OperationLog(title = "新增菜单", businessType = BusinessTypeEnum.INSERT)
  public R save(@RequestBody SysMenuDTO dto) {
    sysMenuService.save(dto);
    return R.success("新增成功");
  }

  @DeleteMapping("{id}")
  @ApiOperation("根据ID删除")
  @OperationLog(title = "删除菜单", businessType = BusinessTypeEnum.DELETE)
  public R delete(@PathVariable String id) {
    sysMenuService.delete(id);
    return R.success("删除成功");
  }

  @PutMapping
  @ApiOperation("根据ID修改")
  @OperationLog(title = "修改菜单", businessType = BusinessTypeEnum.UPDATE)
  public R update(@RequestBody SysMenuDTO dto) {
    sysMenuService.update(dto);
    return R.success("修改成功");
  }

  @GetMapping
  @ApiOperation("查询菜单列表")
  @OperationLog(title = "查询菜单列表", businessType = BusinessTypeEnum.SELECT)
  public R list(@ModelAttribute SysMenuQuery sysMenuQuery) {
    List<SysMenu> sysMenuList = sysMenuService.getMenuList(sysMenuQuery);
    return R.success("查询成功", sysMenuList);
  }

  @GetMapping("{id}")
  @ApiOperation("根据ID查询")
  @OperationLog(title = "根据ID查询", businessType = BusinessTypeEnum.SELECT)
  public R one(@PathVariable String id) {
    SysMenu sysMenu = sysMenuService.getById(id);
    return R.success("查询成功", sysMenu);
  }
}
