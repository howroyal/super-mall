/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月25日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.model;

import java.util.List;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月25日
 *@Version:1.1.0
 */
public class CartModel {
	private List<CartProductModel> products;
	private int totalCount;
	private int totalScoreSale;
	private boolean isAllChecked;
	public List<CartProductModel> getProducts() {
		return products;
	}
	public void setProducts(List<CartProductModel> products) {
		this.products = products;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalScoreSale() {
		return totalScoreSale;
	}
	public void setTotalScoreSale(int totalScoreSale) {
		this.totalScoreSale = totalScoreSale;
	}
	public boolean isAllChecked() {
		return isAllChecked;
	}
	public void setAllChecked(boolean isAllChecked) {
		this.isAllChecked = isAllChecked;
	}
}
