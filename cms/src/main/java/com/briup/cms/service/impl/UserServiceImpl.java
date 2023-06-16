package com.briup.cms.service.impl;

import java.util.HashMap;
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

import com.briup.cms.bean.User;
import com.briup.cms.dao.UserDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.IUserService;
import com.briup.cms.util.JwtUtil;
import com.briup.cms.util.ResultCode;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		if (user == null) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			userDao.save(user);
		}
	}

	@Override
	public void updateUser(User user) {
		if (user == null) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			userDao.save(user);
		}
	}

	@Override
	public Page<User> findAll(Integer num, Integer size) {
		if (ObjectUtils.isEmpty(num) || ObjectUtils.isEmpty(size)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			Pageable pageable = PageRequest.of(num, size);
			Page<User> page = userDao.findAll(pageable);
			if (page == null) {
				throw new ServiceException(ResultCode.DATA_NONE);
			} else {
				return page;
			}
		}
	}

	@Override
	public void deleteInBatch(List<Long> ids) {
		if (ids != null && ids.size() > 0) {
			userDao.deleteAllById(ids);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}
	}

	@Override
	public void updateStatus(Long id, String status) {
		if (ObjectUtils.isEmpty(id) || !StringUtils.hasText(status)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			userDao.updateStatus(id, status);
		}
	}

	@Override
	public User findByUsername(String username) {
		if (ObjectUtils.isEmpty(username)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			return userDao.findByUsername(username);
		}
	}

	@Override
	public String findByUsernameAndPassword(String username, String password) {
		if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			User user = userDao.findByUsernameAndPassword(username, password);
			if (user != null) {
				String userId = user.getId().toString();
				HashMap<String, Object> info = new HashMap<String, Object>();
				info.put("username", user.getUsername());
				info.put("rolename", user.getRole().getName());
				String sign = JwtUtil.sign(userId, info);
				return sign;
			} else {
				throw new ServiceException(ResultCode.PARAM_IS_BLANK);
			}
		}
	}

}
