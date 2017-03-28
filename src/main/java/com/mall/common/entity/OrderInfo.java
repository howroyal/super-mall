package com.mall.common.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "order_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class OrderInfo {
	
	@Id
	@SequenceGenerator(name = "order_info")
	@Column(name = "id")
	private Long id;
	
	/**
	 * 订单号
	 */
	@Column(name = "order_no")
	private String orderNo;
	
	/**
	 * 用户id
	 * 
	 */
	@Column(name = "user_id")
	private String userId;
	
	/**
	 * 订单总金额
	 */
	@Column(name = "amount_price")
	private double amountPrice;
	
	/**
	 * 支付方式
	 */
	@Column(name = "payment_type")
	private int paymentType;
	
	/**
	 * 是否支付
	 */
	@Column(name = "is_pay")
	private int isPay;
	
	/**
	 * 是否发货
	 */
	@Column(name = "is_delivery")
	private int isDelivery;
	
	/**
	 * 客户留言
	 */
	@Column(name = "message")
	private String message;
	
	/**
	 * 收货电话
	 */
	@Column(name = "phone")
	private String phone;
	
	/**
	 * 收货人
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 收货地址
	 */
	@Column(name = "address")
	private String address;
	
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	
	private List<OrderDetailInfo> detailInfos;
	
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
	public int getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}
	public int getIsPay() {
		return isPay;
	}
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}
	public int getIsDelivery() {
		return isDelivery;
	}
	public void setIsDelivery(int isDelivery) {
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OrderDetailInfo> getDetailInfos() {
		return detailInfos;
	}
	public void setDetailInfos(List<OrderDetailInfo> detailInfos) {
		this.detailInfos = detailInfos;
	}
	
}
