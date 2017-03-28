/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年5月26日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dto;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年5月26日
 *@Version:1.1.0
 */
public class OrderInfoDto extends PageDto{

	private Long id;
	
	/**
	 * 订单号
	 */
	private String orderNo;
	
	/**
	 * 用户id
	 * 
	 */
	private String userId;
	
	/**
	 * 订单总金额
	 */
	private double amountPrice;
	
	/**
	 * 支付方式
	 */
	private Integer paymentType;
	
	/**
	 * 是否支付
	 */
	private Integer isPay;
	
	/**
	 * 是否发货
	 */
	private Integer isDelivery;
	
	/**
	 * 客户留言
	 */
	private String message;
	
	/**
	 * 收货电话
	 */
	private String phone;
	
	/**
	 * 收货人
	 */
	private String name;
	
	/**
	 * 收货地址
	 */
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getAmountPrice() {
		return amountPrice;
	}

	public void setAmountPrice(double amountPrice) {
		this.amountPrice = amountPrice;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Integer getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(Integer isDelivery) {
		this.isDelivery = isDelivery;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
