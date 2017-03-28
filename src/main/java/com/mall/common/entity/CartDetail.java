package com.mall.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**   
 * 购物车详细数据表
 * @ClassName  ShoppingCartDetial   
 * @Description TODO   
 * @author 王浩  
 * @date   2015-9-17 上午11:06:14   
 *      
 */  

@Entity
@Table(name = "cart_detail", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class CartDetail {

	/**
	 * id
	 */
	@Id
	@SequenceGenerator(name = "cart_detail")
	@Column(name = "id")
	private Long id;
	
	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private String userId;
	
	/**
	 * 商品id
	 */
	@Column(name = "goods_id")
	private String goodsId;
	
	/**
	 * 商品数量
	 */
	@Column(name = "amount")
	private int amount;
	
	/**
	 * 是否选中 0=默认选中， 1=未选中
	 */
	@Column(name = "is_choiced")
	private int isChoiced;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 *更新时间
	 */
	@Column(name = "update_time")
	private String updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getIsChoiced() {
		return isChoiced;
	}
	public void setIsChoiced(int isChoiced) {
		this.isChoiced = isChoiced;
	}
}
