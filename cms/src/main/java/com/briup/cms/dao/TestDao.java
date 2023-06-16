package com.briup.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.cms.bean.Test;

public interface TestDao extends JpaRepository<Test, Long> {

}
