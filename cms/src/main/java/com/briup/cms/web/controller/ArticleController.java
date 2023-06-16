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

import com.briup.cms.bean.Article;
import com.briup.cms.service.IArticleService;
import com.briup.cms.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "资讯管理模块")
@RestController
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private IArticleService articleService;

	@ApiOperation(value = "新增资讯", notes = "ID自增")
	@PostMapping("/")
	public Result saveArticle(@RequestBody Article article) {
		articleService.saveArticle(article);
		return Result.success();
	}

	@ApiOperation(value = "更新资讯", notes = "ID必传值")
	@PutMapping("/")
	public Result updateArticle(@RequestBody Article article) {
		articleService.updateArticle(article);
		return Result.success();
	}

	@ApiOperation("分页查询资讯")
	@ApiImplicitParams({ @ApiImplicitParam(name = "num", value = "当前页码", required = true, defaultValue = "0"),
			@ApiImplicitParam(name = "size", value = "每夜条数", required = true, defaultValue = "5") })
	@GetMapping("/")
	public Result findAll(Integer num, Integer size) {
		Page<Article> page = articleService.findAll(num, size);
		return Result.success(page);
	}

	@ApiOperation("批量删除资讯")
	@ApiImplicitParam(name = "ids", value = "ID集合")
	@DeleteMapping("/")
	public Result deleteInBatch(@RequestBody List<Long> ids) {
		articleService.deleteInBatch(ids);
		return Result.success();
	}

	@ApiOperation(value = "审核资讯", notes = "未审核0，审核通过1，审核失败2")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
			@ApiImplicitParam(name = "status", value = "状态", required = true) })
	@PutMapping("/{id}/{status}")
	public Result updateStatus(@PathVariable("id") Long id, @PathVariable("status") String status) {
		articleService.updateStatus(id, status);
		return Result.success();
	}

	@ApiOperation("根据类别id分页查询资讯并按照阅读量进行降序排")
	@ApiImplicitParams({ @ApiImplicitParam(name = "categoryId", value = "类别id", required = true),
			@ApiImplicitParam(name = "num", value = "当前页码", required = true, defaultValue = "0"),
			@ApiImplicitParam(name = "size", value = "每夜条数", required = true, defaultValue = "5") })
	@GetMapping("/byCategoryId")
	public Result findByCategoryIdOrderByReadTimesDesc(Long categoryId, Integer num, Integer size) {
		Page<Article> page = articleService.findByCategoryIdOrderByReadTimesDesc(categoryId, num, size);
		return Result.success(page);
	}

	@ApiOperation("根据用户id查询资讯")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户id", required = true),
			@ApiImplicitParam(name = "num", value = "当前页码", required = true, defaultValue = "0"),
			@ApiImplicitParam(name = "size", value = "每夜条数", required = true, defaultValue = "5") })
	@GetMapping("/byUserId")
	public Result findByUserId(Long userId, Integer num, Integer size) {
		Page<Article> page = articleService.findByUserId(userId, num, size);
		return Result.success(page);
	}

}
