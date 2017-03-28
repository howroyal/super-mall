package com.mall.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**   
 * 商品类
 * @ClassName  GoodsInfo   
 * @Description TODO   
 * @author 王浩  
 * @date   2015-8-11 上午11:49:01   
 *      
 */  
@Entity
@Table(name = "goods_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class GoodsInfo {

	@Id
	@SequenceGenerator(name = "goods_info")
	@Column(name = "id")
	private Long id;
	
	/**
	 * 商品编码 uuid
	 */
	
	@Column(name = "code")
	private String code;
	/**
	 * 商品名
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 所属类别，品牌
	 */
	@Column(name = "category_id")
	private Long categoryId;
	
	/**
	 * 主图地址
	 */
	@Column(name = "main_img")
	private String mainImg;
	
	/**   
	 * 图片地址
	 */ 
	@Column(name = "pre_img")
	private String preImg;
	
	/**
	 * 商品库存
	 */
	@Column(name = "stock")
	private int stock;
	
	/**
	 * 销量
	 */
	@Column(name = "sales")
	private int sales;
	
	/**
	 * 上架时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 点击数量
	 */
	@Column(name = "clicks")
	private int clicks;
	
	/**
	 * 市场价
	 */
	@Column(name = "market_price")
	private double marketPrice;
	
	/**
	 * 本网价
	 */
	@Column(name = "price")
	private double price;
	
	/**
	 * 商品描述
	 */
	@Column(name = "description")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public String getPreImg() {
		return preImg;
	}

	public void setPreImg(String preImg) {
		this.preImg = preImg;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
