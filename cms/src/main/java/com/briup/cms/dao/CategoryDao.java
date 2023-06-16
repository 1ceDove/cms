package com.briup.cms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.briup.cms.bean.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

	@Transactional
	@Modifying
	@Query("update Category c set c.no = ?2 where c.id = ?1")
	void updateNo(Long id, Long no);

	Page<Category> findByOrderByNoAsc(Pageable pageable);

}
