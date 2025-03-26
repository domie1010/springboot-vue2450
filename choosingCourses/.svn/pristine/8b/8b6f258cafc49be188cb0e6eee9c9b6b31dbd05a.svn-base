package com.longwang.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longwang.entity.Notice;
import com.longwang.service.NoticeService;

/**
 * 公告控制器
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/admin/notice")
public class NoticeAdminController {
	
	@Resource
	private NoticeService noticeService;
	
	/**
	 * 分页查询公告
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value="page",required=false)Integer page,@RequestParam(value="pageSize",required=false)Integer pageSize){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("errorNo", 0);
		resultMap.put("data", noticeService.list(page-1, pageSize));
		resultMap.put("total", noticeService.getCount());
		return resultMap;
	}
	
	/**
	 * 根据ID查找公告
	 * @param noticeId
	 * @return
	 */
	@RequestMapping("/findById")
	public Map<String, Object> findById(Integer noticeId) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("errorNo", 0);
		resultMap.put("data", noticeService.findById(noticeId));
		return resultMap;
	}
	
	/**
	 * 添加或者修改公告
	 * @param notice
	 * @return
	 */
	@RequestMapping("/save")
	public Map<String, Object> save(Notice notice){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		notice.setPublishDate(new Date());
		noticeService.save(notice);
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		return resultMap;
	}
	
	/**
	 * 批量删除公告
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam(value="noticeId")String ids){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++) {
			noticeService.delete(Integer.parseInt(idsStr[i]));
		}
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		return resultMap;
	}

}
