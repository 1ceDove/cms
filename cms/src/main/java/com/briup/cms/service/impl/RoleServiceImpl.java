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

import com.briup.cms.bean.Role;
import com.briup.cms.dao.RoleDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.IRoleService;
import com.briup.cms.util.ResultCode;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public void saveRole(Role role) {
		if (role != null) {
			roleDao.save(role);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}
	}

	@Override
	public void updateRole(Role role) {
		if (role != null) {
			roleDao.save(role);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}
	}

	@Override
	public Page<Role> findAll(Integer num, Integer size) {
		if (ObjectUtils.isEmpty(num) || ObjectUtils.isEmpty(size)) {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		} else {
			Pageable pageable = PageRequest.of(num, size);
			Page<Role> page = roleDao.findAll(pageable);
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
			roleDao.deleteAllById(ids);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}

	}

}
