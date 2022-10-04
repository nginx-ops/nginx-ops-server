package io.github.nginx.ops.server.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.github.nginx.ops.server.comm.annotation.OperationLog;
import io.github.nginx.ops.server.comm.domain.vo.R;
import io.github.nginx.ops.server.comm.enums.BusinessTypeEnum;
import io.github.nginx.ops.server.system.domain.SysUser;
import io.github.nginx.ops.server.system.domain.dto.SysUserRoleDTO;
import io.github.nginx.ops.server.system.domain.query.SysUserQuery;
import io.github.nginx.ops.server.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/24 9:50
 */
@Api(tags = "用户管理接口")
@Slf4j
@RestController
@RequestMapping("sys/user")
@RequiredArgsConstructor
public class SysUserController {

  private final SysUserService service;

  @PostMapping
  @ApiOperation("新增用户")
  @OperationLog(title = "新增用户", businessType = BusinessTypeEnum.INSERT)
  public R save(@RequestBody SysUserRoleDTO dto) {
    service.save(dto);
    return R.success("新增成功");
  }

  @DeleteMapping("{id}")
  @ApiOperation("根据ID删除")
  @OperationLog(title = "删除用户", businessType = BusinessTypeEnum.DELETE)
  public R delete(@PathVariable String id) {
    service.delete(id);
    return R.success("删除成功");
  }

  @PutMapping("{id}")
  @ApiOperation("根据ID修改")
  @OperationLog(title = "修改用户", businessType = BusinessTypeEnum.UPDATE)
  public R update(@PathVariable String id, @RequestBody SysUserRoleDTO dto) {
    service.update(id, dto);
    return R.success("修改成功");
  }

  @GetMapping
  @ApiOperation("查询用户列表")
  @OperationLog(title = "查询用户列表", businessType = BusinessTypeEnum.SELECT)
  public R list(@ModelAttribute SysUserQuery query) {
    List<SysUser> sysUserList = service.list(query);
    return R.success("查询成功", sysUserList);
  }

  @GetMapping("page")
  @ApiOperation("分页查询用户列表")
  @OperationLog(title = "分页查询用户列表", businessType = BusinessTypeEnum.SELECT)
  public R pageList(@ModelAttribute SysUserQuery query) {
    Page<SysUser> page = PageHelper.startPage(query);
    List<SysUser> sysUserList = service.list(query);
    return R.success("查询成功", page);
  }

  @GetMapping("{id}")
  @ApiOperation("根据ID查询")
  @OperationLog(title = "根据ID查询", businessType = BusinessTypeEnum.SELECT)
  public R one(@PathVariable String id) {
    // SysUserDTO sysUserDTO = service.getOne(id);
    return R.success("查询成功", null);
  }
}
