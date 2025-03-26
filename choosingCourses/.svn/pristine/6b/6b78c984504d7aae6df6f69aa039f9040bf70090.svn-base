package com.longwang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 管理员实体
 * @author LongWang
 *
 */
@Entity
@Table(name="t_admin")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adminId; // 管理员Id
	
	@Column(length=200)
	private String userName;  // 用户名
	
	@Column(length=200)
	private String password;  // 密码
	
	@Column(length=200)
	private String trueName;  // 真实姓名
	
	@Column(length=200)
	private String headPortrait;  // 头像
	
	@Column(length=50)
	private String sex;  // 性别
	
	@Column(length=500)
	private String signature;  // 备注
	
	@Column(length=200)
	private String phone;  // 电话
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Admin(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}



	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
