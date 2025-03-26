package com.longwang.service;

import java.util.List;

import com.longwang.entity.Timeline;

/**
 * 时光轴Service接口
 * @author LongWang
 *
 */
public interface TimelineService {

	
	/**
	 * 分页查询时光轴
	 * @param timeline
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Timeline> list(Timeline timeline,Integer page,Integer pageSize);
	
	
	/**
	 * 获取总记录数
	 * @param timeline
	 * @return
	 */
	public Long getCount(Timeline timeline);
	
	/**
	 * 根据ID查找时光轴
	 * @param id
	 * @return
	 */
	public Timeline findById(Integer id);
	
	/**
	 * 添加或修改时光轴
	 * @param timeline
	 */
	public void save(Timeline timeline);
	
	/**
	 * 删除时光轴
	 * @param id
	 */
	public void delete(Integer id);
	
}
