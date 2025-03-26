package com.longwang.service;

import java.util.List;

import com.longwang.entity.Comment;

/**
 * 评论Service接口
 * @author LongWang
 *
 */
public interface CommentService {

	/**
	 * 分页查询评论
	 * @param comment
	 * @param s_bCommentDate
	 * @param s_eCommentDate
	 * @param page
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	public List<Comment> list(Comment comment,String s_bCommentDate,String s_eCommentDate,Integer page,Integer pageSize,Integer userId);
	
	/**
	 * 分页查询留言
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Comment> massageList(Integer page,Integer pageSize);
	
	/**
	 * 获取总记录数
	 * @param comment
	 * @param s_bPublishDate
	 * @param s_ePublishDate
	 * @param userId
	 * @return
	 */
	public Long getCount(Comment comment,String s_bPublishDate,String s_ePublishDate,Integer userId);
	
	/**
	 * 获取总记录数(留言)
	 * @return
	 */
	public Long getCount2();
	
	/**
	 * 删除评论
	* @Title: delete 
	*  @param id  评论ID 
	* @return void    返回类型 
	* @throws
	 */
	public void delete(Integer id);
	
	/**
	 * 添加评论
	* @Title: add 
	*  @param comment  评论实体 
	* @return void    返回类型 
	* @throws
	 */
	public void add(Comment comment);
	
	/**
	 * 根据评论Id获取文章Id
	 * @param commentId
	 * @return
	 */
	public Integer getArticleId(Integer commentId);
}
