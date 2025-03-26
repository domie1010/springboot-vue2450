package com.longwang.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longwang.entity.Link;
import com.longwang.run.StartupRunner;
import com.longwang.service.LinkService;

/**
 * 友情链接控制器
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/admin/link")
public class LinkAdminController {
	
	@Resource
	private LinkService linkService;
	
	@Resource
	private StartupRunner startupRunner;
	
	/**
	 * 分页查询友情链接
	 * @param link
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/list")
	public Map<String, Object> list(Link link,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="pageSize",required=false)Integer pageSize){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("errorNo", 0);
		resultMap.put("data", linkService.list(link,page-1, pageSize));
		resultMap.put("total", linkService.getCount(link));
		return resultMap;
	}
	
	/**
	 * 根据ID查找友情链接
	 * @param linkId
	 * @return
	 */
	@RequestMapping("/findById")
	public Map<String, Object> findById(Integer linkId) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("errorNo", 0);
		resultMap.put("data", linkService.findById(linkId));
		return resultMap;
	}
	
	/**
	 * 添加或者修改友情链接
	 * @param link
	 * @return
	 */
	@RequestMapping("/save")
	public Map<String, Object> save(Link link){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		linkService.save(link);
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		startupRunner.loadData();
		return resultMap;
	}
	
	/**
	 * 批量删除友情链接
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam(value="linkId")String ids){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++) {
			linkService.delete(Integer.parseInt(idsStr[i]));
		}
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		startupRunner.loadData();
		return resultMap;
	}

}
