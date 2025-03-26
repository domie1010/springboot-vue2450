package com.longwang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.longwang.entity.Admin;

/**
 * 用户Repository接口
 * @author LongWang
 *
 */
public interface AdminRepository extends JpaRepository<Admin, Integer>,JpaSpecificationExecutor<Admin> {

	
	public Admin findByUserNameAndPassword(String userName,String password);
}
