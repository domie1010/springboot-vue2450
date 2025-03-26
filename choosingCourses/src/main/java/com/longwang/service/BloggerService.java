package com.longwang.service;

import com.longwang.entity.Blogger;

/**
 * 博主Service接口
 * @author LongWang
 *
 */
public interface BloggerService {

	
	/**
	 * 查询博主信息
	 * @return
	 */
	public Blogger find();
	
	/**
	 * 添加或修改博主
	 * @param blogger
	 */
	public void save(Blogger blogger);
	
}
