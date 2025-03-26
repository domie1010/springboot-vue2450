package com.longwang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 用户实体
 * @author LongWang
 *
 */
@Entity
@Table(name = "t_user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId; // 用户Id

  @Column
  private String username;// 用户名

  @Column
  private String password;// 密码

  @Column(length = 200)
  private String openId; // openID

  @Column(length = 200)
  private String nickname; // 昵称

  @Column(length = 200)
  private String headPortrait; // 头像

  @Column(length = 50)
  private String sex; // 性别

  @Column(nullable = false, columnDefinition = "timestamp", updatable = false)
  private Date registrationDate; // 注册日期

  private Date latelyLoginTime; // 最近登录时间

  private String phone;// 手机号

  private java.sql.Date birthday;// 生日

  private String articleIds;// 收藏文章id

  private String userIds;// 关注的用户id

  private String momo;// 备注

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getHeadPortrait() {
    return headPortrait;
  }

  public void setHeadPortrait(String headPortrait) {
    this.headPortrait = headPortrait;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  @JsonSerialize(using = CustomDateTimeSerializer.class)
  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  @JsonSerialize(using = CustomDateTimeSerializer.class)
  public Date getLatelyLoginTime() {
    return latelyLoginTime;
  }

  public void setLatelyLoginTime(Date latelyLoginTime) {
    this.latelyLoginTime = latelyLoginTime;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public java.sql.Date getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Date birthday) {
    this.birthday = birthday;
  }

  public String getArticleIds() {
    return articleIds;
  }

  public void setArticleIds(String articleIds) {
    this.articleIds = articleIds;
  }

  public String getUserIds() {
    return userIds;
  }

  public void setUserIds(String userIds) {
    this.userIds = userIds;
  }

  public String getMomo() {
    return momo;
  }

  public void setMomo(String momo) {
    this.momo = momo;
  }

}
