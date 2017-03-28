package com.mall.common.vo;

import java.util.Date;
import java.util.List;

import com.mall.common.entity.CategoryInfo;

/**   
 * 商品信息的vo对象
 * @ClassName  GoodsInfoVO   
 * @Description TODO   
 * @author 王浩  
 * @date   2015-8-21 上午09:30:03   
 *      
 */  
public class GoodsInfoVO {

	private Long id;
	
	/**
	 * 商品编码 uuid
	 */
	
	private String code;
	/**
	 * 商品名
	 */
	private String name;
	
	/**
	 * 所属类别，品牌
	 */
	private CategoryInfo category;
	
	/**
	 * 主图地址
	 */
	private String mainImg;
	
	/**   
	 * 图片地址
	 */ 
	private List<String> imgUrl;
	
	/**
	 * 商品库存
	 */
	private int stock;
	
	/**
	 * 销量
	 */
	private int sales;
	
	/**
	 * 上架时间
	 */
	private Date createTime;
	
	/**
	 * 点击数量
	 */
	private int clicks;
	
	/**
	 * 市场价
	 */
	private double marketPrice;
	
	/**
	 * 本网价
	 */
	private double price;
	
	/**
	 * 商品描述
	 */
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

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
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

	public CategoryInfo getCategory() {
		return category;
	}

	public void setCategory(CategoryInfo category) {
		this.category = category;
	}

	public List<String> getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(List<String> imgUrl) {
		this.imgUrl = imgUrl;
	}
}
