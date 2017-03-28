/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年6月2日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年6月2日
 *@Version:1.1.0
 */
@Entity
@Table(name = "balance_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class BalanceInfo {

	@Id
	@SequenceGenerator(name = "id")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "money")
	private Float money;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "is_valid")
	private Integer isValid;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "update_time")
	private Date updateTime;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
