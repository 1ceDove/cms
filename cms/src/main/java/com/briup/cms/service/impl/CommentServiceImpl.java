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

import com.briup.cms.bean.Comment;
import com.briup.cms.dao.CommentDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.ICommentService;
import com.briup.cms.util.ResultCode;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CommentServiceImpl implements ICommentService {
	@Autowired
	private CommentDao commentDao;

	@Override
	public void saveComment(Comment comment) {
		if (comment != null) {
			commentDao.save(comment);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}

	}

	@Override
	public void updateComment(Comment comment) {
		if (comment != null) {
			commentDao.save(comment);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}

	}

	@Override
	public Page<Comment> findAll(Integer num, Integer size) {
		if (ObjectUtils.isEmpty(num) || ObjectUtils.isEmpty(size)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			Pageable pageable = PageRequest.of(num, size);
			Page<Comment> page = commentDao.findAll(pageable);
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
			commentDao.deleteAllById(ids);
		} else {
			throw new ServiceException(ResultCode.DATA_NONE);
		}
	}

	@Override
	public Page<Comment> findByArticleId(Long articleId, Integer num, Integer size) {
		if (ObjectUtils.isEmpty(articleId) || ObjectUtils.isEmpty(num) || ObjectUtils.isEmpty(size)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			Pageable pageable = PageRequest.of(num, size);
			Page<Comment> page = commentDao.findByArticleId(articleId, pageable);
			if (page != null) {
				return page;
			} else {
				throw new ServiceException(ResultCode.DATA_NONE);
			}
		}
	}

}
