package com.briup.cms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.briup.cms.bean.Article;

public interface ArticleDao extends JpaRepository<Article, Long> {

	@Transactional
	@Modifying
	@Query("update Article a set a.status = ?2 where id = ?1")
	void updateStatus(Long id, String status);

	Page<Article> findByCategoryIdOrderByReadTimesDesc(Long categoryId, Pageable pageable);

	Page<Article> findByUserId(Long userId, Pageable pageable);

}
