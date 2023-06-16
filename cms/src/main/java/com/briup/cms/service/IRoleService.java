package com.briup.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.cms.bean.Role;

public interface IRoleService {
	void saveRole(Role role);

	void updateRole(Role role);

	Page<Role> findAll(Integer num, Integer size);

	void deleteInBatch(List<Long> ids);
}
