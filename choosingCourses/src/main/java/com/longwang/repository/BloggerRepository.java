package com.longwang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.longwang.entity.Blogger;

/**
 * 博主Repository接口
 * @author LongWang
 *
 */
public interface BloggerRepository extends JpaRepository<Blogger, Integer>,JpaSpecificationExecutor<Blogger> {

	
}
