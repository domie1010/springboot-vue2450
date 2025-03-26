package com.longwang.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longwang.entity.Reply;
import com.longwang.service.ReplyService;

/**
 * 回复Controller层
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/admin/reply")
public class ReplyAdminController {
	
	
	@Resource
	private ReplyService replyService;

	/**
	 * 分页查询回复
	 * @param reply
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/list")
	public Map<String, Object> list(Reply reply,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="pageSize",required=false)Integer pageSize){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		List<Reply> replyList=replyService.list(reply,page-1, pageSize);
		Long total=replyService.getCount();
		resultMap.put("errorNo", 0);
		resultMap.put("data", replyList);
		resultMap.put("total", total);
		return resultMap;
	}
	
	/**
	 * 删除回复
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam(value="replyId")String ids){
		String []idsStr=ids.split(",");
		Map<String, Object> resultMap=new HashMap<String, Object>();
		for(int i=0;i<idsStr.length;i++) {
			replyService.delete(Integer.parseInt(idsStr[i]));
		}
		resultMap.put("errorNo", 0);
		resultMap.put("data", 1);
		return resultMap;
	}
	
}
