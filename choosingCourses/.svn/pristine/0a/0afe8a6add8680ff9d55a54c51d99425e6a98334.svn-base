package com.longwang.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longwang.entity.Music;
import com.longwang.run.StartupRunner;
import com.longwang.service.MusicService;

/**
 * 音乐控制器
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/admin/music")
public class MusicAdminController {
	
	@Resource
	private MusicService musicService;
	
	@Resource
	private StartupRunner startupRunner;
	
	/**
	 * 分页查询音乐
	 * @param music
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/list")
	public Map<String, Object> list(Music music,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="pageSize",required=false)Integer pageSize){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("errorNo", 0);
		resultMap.put("data", musicService.list(music,page-1, pageSize));
		resultMap.put("total", musicService.getCount(music));
		return resultMap;
	}
	
	/**
	 * 根据ID查找音乐
	 * @param musicId
	 * @return
	 */
	@RequestMapping("/findById")
	public Map<String, Object> findById(Integer musicId) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("errorNo", 0);
		resultMap.put("data", musicService.findById(musicId));
		return resultMap;
	}
	
	/**
	 * 添加或者修改音乐
	 * @param music
	 * @return
	 */
	@RequestMapping("/save")
	public Map<String, Object> save(Music music){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		musicService.save(music);
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		startupRunner.loadData();
		return resultMap;
	}
	
	/**
	 * 批量删除音乐
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam(value="musicId")String ids){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++) {
			musicService.delete(Integer.parseInt(idsStr[i]));
		}
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		startupRunner.loadData();
		return resultMap;
	}

}
