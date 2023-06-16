package com.briup.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.briup.cms.bean.User;

public interface UserDao extends JpaRepository<User, Long> {
	@Transactional
	@Modifying
	@Query("update User u set u.status = ?2 where u.id = ?1")
	void updateStatus(Long id, String status);

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);
}
