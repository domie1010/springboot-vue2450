package com.longwang.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longwang.entity.Comment;
import com.longwang.entity.User;
import com.longwang.service.ArticleService;
import com.longwang.service.CommentService;
import com.longwang.service.ReplyService;
import com.longwang.service.UserService;
import com.longwang.util.StringUtil;

/**
 * 评论Controller层
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/admin/comment")
public class CommentAdminController {

  @Resource
  private CommentService commentService;

  @Resource
  private UserService userService;

  @Resource
  private ReplyService replyService;

  @Resource
  private ArticleService articleService;

  /**
   * 分页查询评论
  * @Title: list  
  * @param comment  评论实体
  * @param commentDates  时间段 (搜索用到)
  * @param page  当前页
  * @param limit  每页记录数
  * @param trueName  昵称
  * @return  参数说明 
  * @return Map<String,Object>    返回类型 
  * @throws
   */
  @RequestMapping("/list")
  public Map<String, Object> list(Comment comment,
      @RequestParam(value = "commentDates", required = false) String commentDates,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "pageSize", required = false) Integer pageSize,
      @RequestParam(value = "nickname", required = false) String nickname) {
    String s_bCommentDate = null; // 开始时间
    String s_eCommentDate = null; // 结束时间
    if (StringUtil.isNotEmpty(commentDates)) {
      String[] strs = commentDates.split(" - "); // 拆分时间段
      s_bCommentDate = strs[0];
      s_eCommentDate = strs[1];
    }
    Integer userId = null;
    Map<String, Object> resultMap = new HashMap<String, Object>();
    if (StringUtil.isNotEmpty(nickname)) {
      User user = userService.findByTrueName(nickname);
      if (user != null) {
        userId = user.getUserId();
      }
      if (userId == null) {
        resultMap.put("errorInfo", "用户昵称不存在，没有评论！");
      } else {
        resultMap.put("errorNo", 0);
      }
    } else {
      resultMap.put("errorNo", 0);
    }
    List<Comment> commentList = commentService.list(comment, s_bCommentDate, s_eCommentDate, page - 1, pageSize,
        userId);
    Long total = commentService.getCount(comment, s_bCommentDate, s_eCommentDate, userId);
    resultMap.put("data", commentList);
    resultMap.put("total", total);
    return resultMap;
  }

  /**
   * 删除评论
   * @param ids
   * @return
   */
  @RequestMapping("/delete")
  public Map<String, Object> delete(@RequestParam(value = "commentId") String ids) {
    String[] idsStr = ids.split(","); // 拆分ids字符串
    Map<String, Object> resultMap = new HashMap<String, Object>();
    for (int i = 0; i < idsStr.length; i++) {
      Integer articleId = commentService.getArticleId(Integer.parseInt(idsStr[i]));
      commentService.delete(Integer.parseInt(idsStr[i]));
      if (articleId != null) {
        articleService.reduceComment(articleId);
      }
    }
    resultMap.put("errorNo", 0);
    resultMap.put("data", 1);
    return resultMap;
  }

}
