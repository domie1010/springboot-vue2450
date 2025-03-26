package com.longwang.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longwang.entity.Blogger;
import com.longwang.run.StartupRunner;
import com.longwang.service.BloggerService;

/**
 * 博主控制器
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/admin/blogger")
public class BloggerAdminController {
	
	@Resource
	private BloggerService bloggerService;
	
	@Resource
	private StartupRunner startupRunner;
	
	/**
	 * 查找博主
	 * @param bloggerId
	 * @return
	 */
	@RequestMapping("/find")
	public Map<String, Object> find() {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("errorNo", 0);
		resultMap.put("data", bloggerService.find());
		return resultMap;
	}
	
	/**
	 * 添加或者修改博主
	 * @param blogger
	 * @return
	 */
	@RequestMapping("/save")
	public Map<String, Object> save(Blogger blogger){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		bloggerService.save(blogger);
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		startupRunner.loadData();
		return resultMap;
	}

}
