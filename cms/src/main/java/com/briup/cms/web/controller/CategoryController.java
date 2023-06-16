package com.briup.cms.web.controller;

import java.util.List;

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

import com.briup.cms.bean.Category;
import com.briup.cms.service.ICategoryService;
import com.briup.cms.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "类别管理模块")
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;

	@ApiOperation(value = "新增类别", notes = "ID自增")
	@PostMapping("/")
	public Result saveCategory(@RequestBody Category category) {
		categoryService.saveCategory(category);
		return Result.success();
	}

	@ApiOperation(value = "更新类别", notes = "ID必传值")
	@PutMapping("/")
	public Result updateCategory(@RequestBody Category category) {
		categoryService.updateCategory(category);
		return Result.success();
	}

	@ApiOperation("分页查询类别")
	@ApiImplicitParams({ @ApiImplicitParam(name = "num", value = "当前页码", required = true, defaultValue = "0"),
			@ApiImplicitParam(name = "size", value = "每页条数", required = true, defaultValue = "5") })
	@GetMapping("/")
	public Result findAll(Integer num, Integer size) {
		Page<Category> page = categoryService.findAll(num, size);
		return Result.success(page);
	}

	@ApiOperation("批量删除类别")
	@ApiImplicitParam(name = "ids", value = "ID集合")
	@DeleteMapping("/")
	public Result deleteInBatch(@RequestBody List<Long> ids) {
		categoryService.deleteInBatch(ids);
		return Result.success();
	}

	@ApiOperation("更新类别序号")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
			@ApiImplicitParam(name = "no", value = "序号", required = true) })
	@PutMapping("/{id}/{no}")
	public Result updateNo(@PathVariable("id") Long id, @PathVariable("no") Long no) {
		categoryService.updateNo(id, no);
		return Result.success();
	}

	@ApiOperation("按照序号升序分页获取类别信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "num", value = "当前页码", required = true, defaultValue = "0"),
			@ApiImplicitParam(name = "size", value = "每夜条数", required = true, defaultValue = "5") })
	@GetMapping("/byNoAsc")
	public Result findAllInPage(Integer num, Integer size) {
		Page<Category> page = categoryService.findAllInPage(num, size);
		return Result.success(page);
	}

}
