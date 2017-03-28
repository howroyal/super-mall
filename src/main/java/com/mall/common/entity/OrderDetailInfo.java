package com.mall.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "order_detail_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class OrderDetailInfo {

	@Id
	@SequenceGenerator(name = "order_detail_info")
	@Column(name = "id")
	private Long id;
	
	/**
	 * 订单id
	 */
	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "goods_id")
	private String goodsId;
	
	@Column(name = "goods_name")
	private String goodsName;
	
	@Column(name = "main_img_url")
	private String mainImgUrl;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@Column(name = "create_time")
	private Date createTime = new Date();
	
	@Column(name = "update_time")
	private Date updateTime = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getMainImgUrl() {
		return mainImgUrl;
	}

	public void setMainImgUrl(String mainImgUrl) {
		this.mainImgUrl = mainImgUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
