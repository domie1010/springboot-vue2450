package com.longwang.service;

import java.util.List;

import com.longwang.entity.Music;

/**
 * 音乐Service接口
 * @author LongWang
 *
 */
public interface MusicService {

	
	/**
	 * 分页查询音乐
	 * @param music
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Music> list(Music music,Integer page,Integer pageSize);
	
	
	/**
	 * 获取总记录数
	 * @param music
	 * @return
	 */
	public Long getCount(Music music);
	
	/**
	 * 根据ID查找音乐
	 * @param id
	 * @return
	 */
	public Music findById(Integer id);
	
	/**
	 * 添加或修改音乐
	 * @param music
	 */
	public void save(Music music);
	
	/**
	 * 删除音乐
	 * @param id
	 */
	public void delete(Integer id);
	
}
