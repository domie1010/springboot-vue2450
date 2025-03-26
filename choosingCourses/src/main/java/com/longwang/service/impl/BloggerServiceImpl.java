package com.longwang.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longwang.entity.Blogger;
import com.longwang.repository.BloggerRepository;
import com.longwang.service.BloggerService;

/**
 * 博主Service接口实现类
 * @author LongWang
 *
 */
@Service("bloggerService")
@Transactional
public class BloggerServiceImpl implements BloggerService {

	@Resource
	private BloggerRepository bloggerRepository;

	@Override
	public Blogger find() {
		// TODO Auto-generated method stub
		return bloggerRepository.findAll().get(0);
	}

	@Override
	public void save(Blogger blogger) {
		// TODO Auto-generated method stub
		bloggerRepository.save(blogger);
	}
	

}
