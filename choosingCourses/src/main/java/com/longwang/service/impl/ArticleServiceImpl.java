package com.longwang.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longwang.entity.Article;
import com.longwang.repository.ArticleRepository;
import com.longwang.service.ArticleService;
import com.longwang.util.StringUtil;

/**
 * 文章Service接口实现类
 * 
 * @author LongWang
 *
 */
@Service("articleService")
@Transactional

public class ArticleServiceImpl implements ArticleService {

  @Resource
  private ArticleRepository articleRepository;

  @Override
  public List<Article> list(Article article, String s_bPublishDate, String s_ePublishDate, Integer page,
      Integer pageSize) {
    // TODO Auto-generated method stub
    List<Order> orders = new ArrayList<Order>();
    orders.add(new Order(Direction.DESC, "isTop"));
    orders.add(new Order(Direction.DESC, "publishDate"));
    Page<Article> pageArticle = articleRepository.findAll(new Specification<Article>() {

      @Override
      public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // TODO Auto-generated method stub
        Predicate predicate = cb.conjunction();
        if (StringUtil.isNotEmpty(s_bPublishDate)) {
          predicate.getExpressions()
              .add(cb.greaterThanOrEqualTo(root.get("publishDate").as(String.class), s_bPublishDate));
        }
        if (StringUtil.isNotEmpty(s_ePublishDate)) {
          predicate.getExpressions()
              .add(cb.lessThanOrEqualTo(root.get("publishDate").as(String.class), s_ePublishDate));
        }
        if (article != null) {
          if (StringUtil.isNotEmpty(article.getTitle())) {
            predicate.getExpressions().add(cb.like(root.get("title"), "%" + article.getTitle().trim() + "%"));
          }
          if (article.getClassify() != null) {
            predicate.getExpressions()
                .add(cb.equal(root.get("classify").get("classifyId"), article.getClassify().getClassifyId()));
          }
          if (article.getUserId() != null) {
            predicate.getExpressions().add(cb.equal(root.get("userId"), article.getUserId()));
          }
        }
        return predicate;
      }
    }, PageRequest.of(page, pageSize, Sort.by(orders)));
    return pageArticle.getContent();
  }

  @Override
  public List<Article> list() {
    return articleRepository.findAll();
  }

  @Override
  public Long getCount(Article article, String s_bPublishDate, String s_ePublishDate) {
    // TODO Auto-generated method stub
    Long count = articleRepository.count(new Specification<Article>() {

      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      @Override
      public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // TODO Auto-generated method stub
        Predicate predicate = cb.conjunction();
        if (StringUtil.isNotEmpty(s_bPublishDate)) {
          predicate.getExpressions()
              .add(cb.greaterThanOrEqualTo(root.get("publishDate").as(String.class), s_bPublishDate));
        }
        if (StringUtil.isNotEmpty(s_ePublishDate)) {
          predicate.getExpressions()
              .add(cb.lessThanOrEqualTo(root.get("publishDate").as(String.class), s_ePublishDate));
        }
        if (article != null) {
          if (StringUtil.isNotEmpty(article.getTitle())) {
            predicate.getExpressions().add(cb.like(root.get("title"), "%" + article.getTitle().trim() + "%"));
          }
          if (article.getClassify() != null) {
            predicate.getExpressions()
                .add(cb.equal(root.get("classify").get("classifyId"), article.getClassify().getClassifyId()));
          }
          if (article.getUserId() != null) {
            predicate.getExpressions().add(cb.equal(root.get("userId"), article.getUserId()));
          }
        }
        return predicate;
      }
    });
    return count;
  }

  @Override
  public Article findById(Integer id) {

    return articleRepository.getOne(id);
  }

  @Override
  public void save(Article article) {

    articleRepository.save(article);
  }

  @Override
  public void delete(Integer id) {

    articleRepository.deleteById(id);
  }

  @Override
  public void increaseClick(Integer articleId) {

    articleRepository.increaseClick(articleId);
  }

  @Override
  public void increaseComment(Integer articleId) {

    articleRepository.increaseComment(articleId);
  }

  @Override
  public void reduceComment(Integer articleId) {

    articleRepository.reduceComment(articleId);
  }

  @Override
  public List<Article> getRandomArticle(Integer n) {

    return articleRepository.getRandomArticle(n);
  }

  @Override
  public List<Article> getClickArticle(Integer n) {
    // TODO Auto-generated method stub
    return articleRepository.getClickArticle(n);
  }

  @Override
  public List<Article> getRecommendArticle(Integer n) {
    // TODO Auto-generated method stub
    return articleRepository.getRecommendArticle(n);
  }

  @Override
  public Long getCount() {
    return articleRepository.count();
  }

  @Override
  public List<Article> findByListId(List<Integer> retIds) {
    return articleRepository.findAllById(retIds);
  }

  @Override
  public List<Article> findByUserId(Integer id) {
    return articleRepository.findByUserId(id);
  }

}
