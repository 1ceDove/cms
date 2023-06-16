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

import com.briup.cms.bean.Comment;
import com.briup.cms.service.ICommentService;
import com.briup.cms.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "评论管理模块")
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private ICommentService commentService;

	@ApiOperation(value = "新增评论", notes = "ID自增")
	@PostMapping("/")
	public Result saveComment(@RequestBody Comment comment) {
		commentService.saveComment(comment);
		return Result.success();
	}

	@ApiOperation(value = "更新评论", notes = "ID必传值")
	@PutMapping("/")
	public Result updateComment(@RequestBody Comment comment) {
		commentService.updateComment(comment);
		return Result.success();
	}

	@ApiOperation("分页查询评论")
	@ApiImplicitParams({ @ApiImplicitParam(name = "num", value = "当前页码", required = true, defaultValue = "0"),
			@ApiImplicitParam(name = "size", value = "每页条数", required = true, defaultValue = "5") })
	@GetMapping("/")
	public Result findAll(Integer num, Integer size) {
		Page<Comment> page = commentService.findAll(num, size);
		return Result.success(page);
	}

	@ApiOperation("批量删除评论")
	@ApiImplicitParam(name = "ids", value = "ID集合")
	@DeleteMapping("/")
	public Result deleteInBatch(@RequestBody List<Long> ids) {
		commentService.deleteInBatch(ids);
		return Result.success();
	}

	@ApiOperation("分页获取指定资讯下所有的评论")
	@ApiImplicitParams({ @ApiImplicitParam(name = "articleId", value = "资讯id", required = true),
			@ApiImplicitParam(name = "num", value = "当前页码", required = true, defaultValue = "0"),
			@ApiImplicitParam(name = "size", value = "每页条数", required = true, defaultValue = "5") })
	@GetMapping("/byArticleId")
	public Result findByArticleId(Long articleId, Integer num, Integer size) {
		Page<Comment> page = commentService.findByArticleId(articleId, num, size);
		return Result.success(page);
	}

}
