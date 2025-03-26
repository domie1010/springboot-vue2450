package com.longwang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 博主实体
 * @author LongWang
 *
 */
@Entity
@Table(name="t_blogger")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Blogger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bloggerId; // 博主Id
	
	@Column(length=200)
	private String nickName;  // 昵称
	
	@Column(length=200)
	private String headPortrait;  // 头像
	
	@Column(length=500)
	private String motto;  // 座右铭
	
	@Column(length=500)
	private String signature;  // 个性签名
	
	@Column(length=200)
	private String site;  // 地址

	public Integer getBloggerId() {
		return bloggerId;
	}

	public void setBloggerId(Integer bloggerId) {
		this.bloggerId = bloggerId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
}
