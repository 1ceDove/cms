package com.briup.cms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.cms.bean.Test;
import com.briup.cms.service.ITestService;
import com.briup.cms.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "测试管理模块")
@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private ITestService testService;

	@ApiOperation(value = "新增测试信息", notes = "ID自增")
	@PostMapping("/")
	public Result saveTest(@RequestBody Test test) {
		testService.saveTest(test);
		return Result.success();
	}

}
