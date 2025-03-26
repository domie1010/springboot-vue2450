package com.longwang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 文章实体
 * 
 * @author LongWang
 *
 */
@Entity
@Table(name = "t_article")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer articleId; // 文章Id

  @Column(length = 200)
  private String title; // 文章标题

  @Lob
  @Column(columnDefinition = "TEXT")
  private String content; // 文章内容

  @Transient
  private String contentNoTag; // 博客内容 无网页标签 Lucene分词用到

  private Date publishDate; // 发布日期

  @Column(length = 200)
  private String author; // 作者

  @ManyToOne
  @JoinColumn(name = "classifyId")
  private Classify classify; // 文章类别

  private Integer click; // 点击数

  private Integer commentNum; // 评论数

  private Integer isTop; // 置顶 0否 1是

  private Integer isOriginal; // 原创 0否 1是

  @Column(length = 500)
  private String imageName; // 图片名称

  @Column
  private Integer userId;// 用户id

  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @JsonSerialize(using = CustomDateTimeSerializer.class)
  public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Classify getClassify() {
    return classify;
  }

  public void setClassify(Classify classify) {
    this.classify = classify;
  }

  public Integer getClick() {
    return click;
  }

  public void setClick(Integer click) {
    this.click = click;
  }

  public Integer getCommentNum() {
    return commentNum;
  }

  public void setCommentNum(Integer commentNum) {
    this.commentNum = commentNum;
  }

  public Integer getIsTop() {
    return isTop;
  }

  public void setIsTop(Integer isTop) {
    this.isTop = isTop;
  }

  public Integer getIsOriginal() {
    return isOriginal;
  }

  public void setIsOriginal(Integer isOriginal) {
    this.isOriginal = isOriginal;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getContentNoTag() {
    return contentNoTag;
  }

  public void setContentNoTag(String contentNoTag) {
    this.contentNoTag = contentNoTag;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

}
