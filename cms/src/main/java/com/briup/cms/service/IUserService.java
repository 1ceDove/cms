package com.briup.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.cms.bean.User;

public interface IUserService {
	void saveUser(User user);

	void updateUser(User user);

	Page<User> findAll(Integer num, Integer size);

	void deleteInBatch(List<Long> ids);

	void updateStatus(Long id, String status);

	User findByUsername(String username);

	String findByUsernameAndPassword(String username, String password);
}
