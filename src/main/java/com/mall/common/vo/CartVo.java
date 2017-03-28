package com.mall.common.vo;

import java.util.List;

public class CartVo {

	/**
	 * 商品数量
	 */
	private int totalAmount;
	/**
	 * 总价钱
	 */
	private double totalPrice;
	/**
	 * 总共节省钱
	 */
	private double totalSavePrice;
	
	private List<CartDetailVo> cartDetailVos;
	
	private boolean isAllChecked;

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalSavePrice() {
		return totalSavePrice;
	}

	public void setTotalSavePrice(double totalSavePrice) {
		this.totalSavePrice = totalSavePrice;
	}

	public List<CartDetailVo> getCartDetailVos() {
		return cartDetailVos;
	}

	public void setCartDetailVos(List<CartDetailVo> cartDetailVos) {
		this.cartDetailVos = cartDetailVos;
	}

	public boolean isAllChecked() {
		return isAllChecked;
	}

	public void setAllChecked(boolean isAllChecked) {
		this.isAllChecked = isAllChecked;
	}
	
}
