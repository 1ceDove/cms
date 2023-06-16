package com.briup.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.briup.cms.bean.Test;
import com.briup.cms.dao.TestDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.ITestService;
import com.briup.cms.util.ResultCode;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestServiceImpl implements ITestService {
	@Autowired
	private TestDao testDao;

	@Override
	public void saveTest(Test test) {
		if (test != null) {
			testDao.save(test);
		} else {
			throw new ServiceException(ResultCode.PARAM_IS_BLANK);
		}

	}

}
