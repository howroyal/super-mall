/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年6月5日
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
 *@Title: 用户充值信息
 *@Description:
 *@Author:hao.wang
 *@Since:2016年6月5日
 *@Version:1.1.0
 */
@Entity
@Table(name = "recharge_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class RechargeInfo {
	
	@Id
	@SequenceGenerator(name = "recharge_info")
	@Column(name = "id")
	private Long id; //id
	
	@Column(name = "user_id")
	private Long userId;//用户id
	
	@Column(name = "pay_way")
	private String payWay;//支付渠道 
	
	@Column(name = "pay_account")
	private String payAccount;//支付账户
	
	@Column(name = "recharge_money")
	private String rechargeMoney;//充值金额
	
	@Column(name = "pay_info")
	private String payInfo;//支付详情
	
	@Column(name = "valid")
	private int valid;//验证通过
	
	@Column(name = "create_time")
	private Date createTime;//创建日期
	
	@Column(name = "update_time")
	private Date updateTime;//更新日期

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

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
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
