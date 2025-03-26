package com.longwang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 文章类别实体
 * @author LongWang
 *
 */
@Entity
@Table(name="t_classify")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Classify {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer classifyId;  //类别Id
	
	@NotEmpty(message="文章类别名称不能为空")
	@Column(length=200)
	private String classifyName;  //类别名称

	public Integer getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	
}
