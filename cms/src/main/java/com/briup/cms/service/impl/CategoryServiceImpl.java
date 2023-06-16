package com.briup.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.briup.cms.bean.Category;
import com.briup.cms.dao.CategoryDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.ICategoryService;
import com.briup.cms.util.ResultCode;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public void saveCategory(Category category) {
		if (category != null) {
			categoryDao.save(category);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}
	}

	@Override
	public void updateCategory(Category category) {
		if (category != null) {
			categoryDao.save(category);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}

	}

	@Override
	public Page<Category> findAll(Integer num, Integer size) {
		if (ObjectUtils.isEmpty(num) || ObjectUtils.isEmpty(size)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			Pageable pageable = PageRequest.of(num, size);
			Page<Category> page = categoryDao.findAll(pageable);
			if (page != null) {
				return page;
			} else {
				throw new ServiceException(ResultCode.DATA_NONE);
			}
		}
	}

	@Override
	public void deleteInBatch(List<Long> ids) {
		if (ids != null && ids.size() > 0) {
			categoryDao.deleteAllById(ids);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}
	}

	@Override
	public void updateNo(Long id, Long no) {
		if (ObjectUtils.isEmpty(id) || ObjectUtils.isEmpty(no)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			categoryDao.updateNo(id, no);
		}
	}

	@Override
	public Page<Category> findAllInPage(Integer num, Integer size) {
		if (ObjectUtils.isEmpty(num) || ObjectUtils.isEmpty(size)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			// 方式一
			// Pageable pageable = PageRequest.of(num, size, Sort.by(Direction.ASC, "no"));
			// Page<Category> page = categoryDao.findAll(pageable);
			// 方式二
			Pageable pageable = PageRequest.of(num, size);
			Page<Category> page = categoryDao.findByOrderByNoAsc(pageable);
			if (page != null) {
				return page;
			} else {
				throw new ServiceException(ResultCode.DATA_NONE);
			}

		}
	}

}
