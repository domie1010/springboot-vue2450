package com.longwang.service;

import java.util.List;

import com.longwang.entity.Link;

/**
 * 友情链接Service接口
 * @author LongWang
 *
 */
public interface LinkService {

	
	/**
	 * 分页查询友情链接
	 * @param link
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Link> list(Link link,Integer page,Integer pageSize);
	
	
	/**
	 * 获取总记录数
	 * @param link
	 * @return
	 */
	public Long getCount(Link link);
	
	/**
	 * 根据ID查找友情链接
	 * @param id
	 * @return
	 */
	public Link findById(Integer id);
	
	/**
	 * 添加或修改友情链接
	 * @param link
	 */
	public void save(Link link);
	
	/**
	 * 删除友情链接
	 * @param id
	 */
	public void delete(Integer id);
	
}
