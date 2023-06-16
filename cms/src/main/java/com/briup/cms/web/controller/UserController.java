package com.briup.cms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.cms.bean.User;
import com.briup.cms.service.IUserService;
import com.briup.cms.util.JwtUtil;
import com.briup.cms.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理模块")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@ApiOperation(value = "新增用户", notes = "ID自增")
	@PostMapping("/")
	public Result saveUser(@RequestBody User user) {
		userService.saveUser(user);
		return Result.success();
	}

	@ApiOperation(value = "更新用户", notes = "ID必传值")
	@PutMapping("/")
	public Result updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return Result.success();
	}

	@ApiOperation("分页查询用户")
	@ApiImplicitParams({ @ApiImplicitParam(name = "num", value = "当前页码", required = true, defaultValue = "0"),
			@ApiImplicitParam(name = "size", value = "每页条数", required = true, defaultValue = "5") })
	@GetMapping("/")
	public Result findAll(Integer num, Integer size) {
		Page<User> page = userService.findAll(num, size);
		return Result.success(page);
	}

	@ApiOperation("批量删除用户")
	@ApiImplicitParam(name = "ids", value = "ID集合")
	@DeleteMapping("/")
	public Result deleteInBatch(@RequestBody List<Long> ids) {
		userService.deleteInBatch(ids);
		return Result.success();
	}

	@ApiOperation(value = "解封或禁用用户", notes = "0:正常 1:禁用")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
			@ApiImplicitParam(name = "status", value = "状态", required = true) })
	@PutMapping("/{id}/{status}")
	public Result updateStatus(@PathVariable("id") Long id, @PathVariable("status") String status) {
		userService.updateStatus(id, status);
		return Result.success();
	}

	@ApiOperation("根据用户名获取用户信息")
	@ApiImplicitParam(name = "username", value = "用户名", required = true)
	@GetMapping("/{username}")
	public Result findByUsername(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		return Result.success(user);
	}

	@ApiOperation("登录")
	@ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "用户名", required = true, defaultValue = "采蘑菇的小松鼠"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, defaultValue = "123456") })
	@GetMapping("/login")
	public Result login(String username, String password) {
		String userId = UUID.randomUUID().toString();
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("username", "tom");
		info.put("role", "admin");
		// 生成JWT字符串
		String token = JwtUtil.sign(userId, info);
		return Result.success("token: " + token);
	}

}
