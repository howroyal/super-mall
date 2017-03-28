/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年6月21日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dto;


/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年6月21日
 *@Version:1.1.0
 */
public class RechargeDto extends BaseDto{

	private Long id; //id
	private Long userId;//用户id
	private String payWay;//支付渠道 
	private String payAccount;//支付账户
	private String rechargeMoney;//充值金额
	private String payInfo;//支付详情
	private String valid;//验证通过
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
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	
}
