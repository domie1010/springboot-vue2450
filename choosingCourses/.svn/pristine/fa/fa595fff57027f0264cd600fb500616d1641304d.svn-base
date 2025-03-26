package com.longwang.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longwang.entity.Comment;
import com.longwang.entity.User;
import com.longwang.service.ArticleService;
import com.longwang.service.CommentService;
import com.longwang.service.ReplyService;

/**
 * 评论控制器
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

  @Resource
  private CommentService commentService;

  @Resource
  private ArticleService articleService;

  @Resource
  private ReplyService replyService;

  /**
   * 分页查询评论
   * @param comment
   * @param page
   * @param pageSize
   * @return
   */
  @RequestMapping("/list")
  public Map<String, Object> commentList(Comment comment, @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
    List<Comment> commentList = commentService.list(comment, null, null, page - 1, pageSize, null);
    Long total = commentService.getCount(comment, null, null, null);
    int totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("totalPage", totalPage);
    resultMap.put("data", commentList);
    return resultMap;
  }

  /**
   * 分页查询评论
   * @param page
   * @param pageSize
   * @return
   */
  @RequestMapping("/massageList")
  public Map<String, Object> massageList(@RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
    List<Comment> commentList = commentService.massageList(page - 1, pageSize);
    Long total = commentService.getCount2();
    int totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("totalPage", totalPage);
    resultMap.put("data", commentList);
    return resultMap;
  }

  /**
   * 添加评论
  * @Title: add  
  * @param comment  评论实体
  * @return  参数说明 
  * @return Map<String,Object>    返回类型 
  * @throws
   */
  @RequestMapping("/add")
  public Map<String, Object> add(Comment comment, HttpSession session) {
    User currentUser = (User) session.getAttribute("user");
    Map<String, Object> resultMap = new HashMap<String, Object>();
    comment.setCommentDate(new Date());
    comment.setUser(currentUser);
    commentService.add(comment);
    if (comment.getArticle() != null) {
      articleService.increaseComment(comment.getArticle().getArticleId());
    }
    resultMap.put("comment", comment);
    resultMap.put("success", true);
    return resultMap;
  }

}
