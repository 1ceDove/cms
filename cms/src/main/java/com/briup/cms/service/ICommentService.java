package com.briup.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.cms.bean.Comment;

public interface ICommentService {
	// 新增评论
	void saveComment(Comment comment);

	// 更新评论
	void updateComment(Comment comment);

	// 分页查询评论
	Page<Comment> findAll(Integer num, Integer size);

	// 批量删除评论
	void deleteInBatch(List<Long> ids);

	// 分页获取指定文章下所有的评论
	Page<Comment> findByArticleId(Long articleId, Integer num, Integer size);

}
