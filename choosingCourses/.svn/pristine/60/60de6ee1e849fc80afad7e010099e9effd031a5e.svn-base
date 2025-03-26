package com.longwang.service;

import java.util.List;

import com.longwang.entity.Notice;

/**
 * 公告Service接口
 * @author LongWang
 *
 */
public interface NoticeService {

	
	/**
	 * 分页查询公告
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Notice> list(Integer page,Integer pageSize);
	
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getCount();
	
	/**
	 * 根据ID查找公告
	 * @param id
	 * @return
	 */
	public Notice findById(Integer id);
	
	/**
	 * 添加或修改公告
	 * @param notice
	 */
	public void save(Notice notice);
	
	/**
	 * 删除公告
	 * @param id
	 */
	public void delete(Integer id);
	
}
