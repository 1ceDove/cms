package com.briup.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.cms.bean.Article;

public interface IArticleService {
	// 新增资讯
	void saveArticle(Article article);

	// 更新资讯
	void updateArticle(Article article);

	// 分页查询资讯
	Page<Article> findAll(Integer num, Integer size);

	// 批量删除
	void deleteInBatch(List<Long> ids);

	// 审核资讯
	void updateStatus(Long id, String status);

	// 根据类别ID查询，分页获取资讯信息，并且按照阅读量降序排
	Page<Article> findByCategoryIdOrderByReadTimesDesc(Long categoryId, Integer num, Integer size);

	// 根据用户分页获取资讯信息
	Page<Article> findByUserId(Long userId, Integer num, Integer size);

}
