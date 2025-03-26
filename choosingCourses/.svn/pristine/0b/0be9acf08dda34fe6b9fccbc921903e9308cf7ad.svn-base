package com.longwang.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longwang.entity.Classify;
import com.longwang.run.StartupRunner;
import com.longwang.service.ClassifyService;

/**
 * 文章类别控制器
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/admin/classify")
public class ClassifyController {

	@Resource
	private ClassifyService classifyService;
	
	@Resource
	private StartupRunner startupRunner;
	
	@RequestMapping("list")
	public Map<String, Object> list(@RequestParam(value="page",required=false)Integer page,@RequestParam(value="pageSize",required=false)Integer pageSize){
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("errorNo", 0);
		resultMap.put("data", classifyService.list(page-1, pageSize));
		resultMap.put("total", classifyService.getCount());
		return resultMap;
	}
	
	/**
	 * 根据Id查找文章类别
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("findById")
	public Map<String, Object> findById(Integer classifyId){
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("errorNo", 0);
		resultMap.put("data", classifyService.findById(classifyId));
		return resultMap;
	}
	
	/**
	 * 添加或修改文章类别
	 * @param classify
	 * @return
	 */
	@RequestMapping("save")
	public Map<String, Object> save(Classify classify){
		Map<String, Object> resultMap=new HashMap<>();
		classifyService.save(classify);
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		startupRunner.loadData();
		return resultMap;
	}
	
	/**
	 * 批量删除文章类别
	 * @param ids
	 * @return
	 */
	@RequestMapping("delete")
	public Map<String, Object> delete(@RequestParam(value="classifyId")String ids){
		Map<String, Object> resultMap=new HashMap<>();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++) {
			classifyService.delete(Integer.parseInt(idsStr[i]));
		}
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		startupRunner.loadData();
		return resultMap;
	}
	
}
