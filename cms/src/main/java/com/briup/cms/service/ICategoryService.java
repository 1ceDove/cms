package com.briup.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.cms.bean.Category;

public interface ICategoryService {
	// 新增类别
	void saveCategory(Category category);

	// 更新类别
	void updateCategory(Category category);

	// 分页查询类别
	Page<Category> findAll(Integer num, Integer size);

	// 批量删除
	void deleteInBatch(List<Long> ids);

	// 更新类别序号
	void updateNo(Long id, Long no);

	// 按照序号升序分页获取类别信息
	Page<Category> findAllInPage(Integer num, Integer size);
}
