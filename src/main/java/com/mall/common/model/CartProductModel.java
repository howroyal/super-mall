/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月25日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.model;


/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月25日
 *@Version:1.1.0
 */
public class CartProductModel {
	private Long productId;
	private String productCode;
	private String productName;
	private String productPicUrl;
	private Long categoryId;
	private String up;
	private Long supplierId;//供应商id
	private String supplier;//供应商
	private double productScoreSale;
	private int productStock;
	private boolean isChecked;
	private int productCount;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPicUrl() {
		return productPicUrl;
	}
	public void setProductPicUrl(String productPicUrl) {
		this.productPicUrl = productPicUrl;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getUp() {
		return up;
	}
	public void setUp(String up) {
		this.up = up;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public double getProductScoreSale() {
		return productScoreSale;
	}
	public void setProductScoreSale(double productScoreSale) {
		this.productScoreSale = productScoreSale;
	}
	public int getProductStock() {
		return productStock;
	}
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
}
