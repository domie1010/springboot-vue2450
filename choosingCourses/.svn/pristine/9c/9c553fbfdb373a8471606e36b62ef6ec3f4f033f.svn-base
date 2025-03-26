package com.longwang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longwang.entity.Timeline;
import com.longwang.service.TimelineService;
import com.longwang.util.DateUtil;

/**
 * 时光轴控制器
 * 
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/timeline")
public class TimelineController {

	@Resource
	private TimelineService timelineService;

	/**
	 * 查询时光轴
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/list")
	public Map<String, Object> list2() {
		Map<String, Object> resultMap = new HashMap<>();
		List<Timeline> maps = timelineService.list(null, 0, 100);
		if (CollectionUtils.isNotEmpty(maps)) {
			Map<String, Object> jsonMap = new LinkedHashMap<String, Object>();
			for (Timeline map : maps) {
				Map<String, Object> yearMap = (Map<String, Object>) jsonMap.get(map.getYear());
				if (null == yearMap) {
					yearMap = new LinkedHashMap<String, Object>();
					yearMap.put("year", map.getYear());
				}
				Map<String, Object> monthMap = (Map<String, Object>) yearMap.get("month");
				if (null == monthMap) {
					monthMap = new LinkedHashMap<String, Object>();
				}
				List<Map<String, Object>> monthList = (List<Map<String, Object>>) monthMap.get(map.getMonth());
				if (null == monthList) {
					monthList = new ArrayList<>();
				}
				monthMap.remove(map.getMonth());
				Map<String, Object> obj = new HashMap<>();
				obj.put("create_time", DateUtil.formatDate(map.getPublishDate(), "MM月dd日 hh:mm"));
				obj.put("content", map.getContent());
				monthList.add(obj);
				monthMap.put(map.getMonth(), monthList);
				yearMap.remove("month");
				yearMap.put("month", monthMap);
				jsonMap.remove(map.getYear());
				jsonMap.put(map.getYear(), yearMap);
			}
			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
				datas.add((Map<String, Object>) entry.getValue());
			}
			resultMap.put("datas", datas);
			resultMap.put("result", 1);
		}else {
			resultMap.put("data", "还没有时光轴数据！");
		}
		return resultMap;

	}
}
