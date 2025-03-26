package com.longwang.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longwang.entity.Timeline;
import com.longwang.run.StartupRunner;
import com.longwang.service.TimelineService;
import com.longwang.util.DateUtil;

/**
 * 时光轴控制器
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/admin/timeline")
public class TimelineAdminController {
	
	@Resource
	private TimelineService timelineService;
	
	@Resource
	private StartupRunner startupRunner;
	
	/**
	 * 分页查询时光轴
	 * @param timeline
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/list")
	public Map<String, Object> list(Timeline timeline,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="pageSize",required=false)Integer pageSize){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("errorNo", 0);
		resultMap.put("data", timelineService.list(timeline,page-1, pageSize));
		resultMap.put("total", timelineService.getCount(timeline));
		return resultMap;
	}
	
	/**
	 * 根据ID查找时光轴
	 * @param timelineId
	 * @return
	 */
	@RequestMapping("/findById")
	public Map<String, Object> findById(Integer timelineId) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		Map<String, Object> tempMap=new HashMap<String, Object>();
		Timeline timeline=timelineService.findById(timelineId);
		tempMap.put("timelineId", timeline.getTimelineId());
		tempMap.put("year", timeline.getYear());
		tempMap.put("month", timeline.getMonth());
		tempMap.put("timeDate", DateUtil.formatDate(timeline.getPublishDate(), "yyyy-MM-dd HH:mm:ss"));
		tempMap.put("content", timeline.getContent());
		resultMap.put("errorNo", 0);
		resultMap.put("data", tempMap);
		return resultMap;
	}
	
	/**
	 * 添加或者修改时光轴
	 * @param timeline
	 * @return
	 */
	@RequestMapping("/save")
	public Map<String, Object> save(Timeline timeline,String timeDate){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			timeline.setPublishDate(DateUtil.formatString(timeDate, "yyyy-MM-dd HH:mm:ss"));
			System.out.println(DateUtil.formatString(timeDate, "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timelineService.save(timeline);
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		startupRunner.loadData();
		return resultMap;
	}
	
	/**
	 * 批量删除时光轴
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam(value="timelineId")String ids){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++) {
			timelineService.delete(Integer.parseInt(idsStr[i]));
		}
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		startupRunner.loadData();
		return resultMap;
	}

}
