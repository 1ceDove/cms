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
import org.springframework.util.StringUtils;

import com.briup.cms.bean.Article;
import com.briup.cms.dao.ArticleDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.IArticleService;
import com.briup.cms.util.ResultCode;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ArticleServiceImpl implements IArticleService {
	@Autowired
	private ArticleDao articleDao;

	@Override
	public void saveArticle(Article article) {
		if (article != null) {
			articleDao.save(article);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}
	}

	@Override
	public void updateArticle(Article article) {
		if (article != null) {
			articleDao.save(article);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}
	}

	@Override
	public Page<Article> findAll(Integer num, Integer size) {
		if (ObjectUtils.isEmpty(num) || ObjectUtils.isEmpty(size)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			Pageable pageable = PageRequest.of(num, size);
			Page<Article> page = articleDao.findAll(pageable);
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
			articleDao.deleteAllById(ids);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}
	}

	@Override
	public void updateStatus(Long id, String status) {
		if (ObjectUtils.isEmpty(id) || !StringUtils.hasText(status)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			articleDao.updateStatus(id, status);
		}
	}

	@Override
	public Page<Article> findByCategoryIdOrderByReadTimesDesc(Long categoryId, Integer num, Integer size) {
		if (ObjectUtils.isEmpty(categoryId) || ObjectUtils.isEmpty(num) || ObjectUtils.isEmpty(size)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			Pageable pageable = PageRequest.of(num, size);
			Page<Article> page = articleDao.findByCategoryIdOrderByReadTimesDesc(categoryId, pageable);
			if (page != null) {
				return page;
			} else {
				throw new ServiceException(ResultCode.DATA_NONE);
			}
		}
	}

	@Override
	public Page<Article> findByUserId(Long userId, Integer num, Integer size) {
		if (ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(num) || ObjectUtils.isEmpty(size)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			Pageable pageable = PageRequest.of(num, size);
			Page<Article> page = articleDao.findByUserId(userId, pageable);
			if (page != null) {
				return page;
			} else {
				throw new ServiceException(ResultCode.DATA_NONE);
			}
		}
	}

}
