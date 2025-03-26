package com.longwang.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;

import com.longwang.entity.Article;

/**
 * 文章Service接口
 * @author LongWang
 *
 */
public interface ArticleService {

  /**
   * 分页查询文章
   * @param article
   * @param s_bPublishDate
   * @param s_ePublishDate
   * @param page
   * @param pageSize
   * @return
   */
  public List<Article> list(Article article, String s_bPublishDate, String s_ePublishDate, Integer page,
      Integer pageSize);

  /**
   * 所有文章
   * @return
   */
  public List<Article> list();

  /**
   * n条置顶原创文章（博主推荐）
   * @param n
   * @return
   */
  public List<Article> getRecommendArticle(Integer n);

  /**
   * 获取总记录数
   * @param article
   * @param s_bPublishDate
   * @param s_ePublishDate
   * @return
   */
  public Long getCount(Article article, String s_bPublishDate, String s_ePublishDate);

  /**
   * 根据ID查找文章
   * @param id
   * @return
   */
  public Article findById(Integer id);

  /**
   * 添加或修改文章
   * @param article
   */
  public void save(Article article);

  /**
   * 删除文章
   * @param id
   */
  public void delete(Integer id);

  /**
   * 文章浏览量+1
   * @param articleId
   */
  @Modifying
  public void increaseClick(Integer articleId);

  /**
   * 评论数量+1
   * @param articleId
   */
  @Modifying
  public void increaseComment(Integer articleId);

  /**
   * 评论数量-1
   * @param articleId
   */
  @Modifying
  public void reduceComment(Integer articleId);

  /**
   * n条随机文章
   * @param n
   * @return
   */
  public List<Article> getRandomArticle(Integer n);

  /**
   * n条热门文章（点击排行）
   * @param n
   * @return
   */
  public List<Article> getClickArticle(Integer n);

  /**
   * 获取笔记总数
   * @return
   */
  public Long getCount();

  /**
   * 查询用户收藏的文章
   * @param retIds
   * @return
   */
  public List<Article> findByListId(List<Integer> retIds);

  /**
   * 根据用户id查询文章
   * @param id
   * @return
   */
  public List<Article> findByUserId(Integer id);

}
