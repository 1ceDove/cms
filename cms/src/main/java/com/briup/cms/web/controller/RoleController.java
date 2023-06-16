package com.briup.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.cms.bean.Role;
import com.briup.cms.service.IRoleService;
import com.briup.cms.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "角色管理模块")
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IRoleService roleService;

	@ApiOperation(value = "新增角色", notes = "ID自增")
	@PostMapping("/")
	public Result saveRole(@RequestBody Role role) {
		roleService.saveRole(role);
		return Result.success();
	}

	@ApiOperation(value = "更新角色", notes = "ID必传值")
	@PutMapping("/")
	public Result updateRole(@RequestBody Role role) {
		roleService.updateRole(role);
		return Result.success();
	}

	@ApiOperation("分页查询角色")
	@ApiImplicitParams({ @ApiImplicitParam(name = "num", value = "当前页码", required = true, defaultValue = "0"),
			@ApiImplicitParam(name = "size", value = "每页条数", required = true, defaultValue = "5") })
	@GetMapping("/")
	public Result findAll(Integer num, Integer size) {
		Page<Role> page = roleService.findAll(num, size);
		return Result.success(page);
	}

	@ApiOperation("批量删除角色")
	@ApiImplicitParam(name = "ids", value = "ID集合")
	@DeleteMapping("/")
	public Result deleteInBatch(@RequestBody List<Long> ids) {
		roleService.deleteInBatch(ids);
		return Result.success();
	}

}
