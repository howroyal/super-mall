/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2015年12月21日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dto;


/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2015年12月21日
 *@Version:1.1.0
 */
public class OrderSubmitDto {

	private String way;//付款方式
	private String addrId;//收货地址id
	private String products;//商品列表
	private String source;//订单来源 1=pc 2=手机客户端
	private String type;// 订单类型
	private String remark;//订单备注
	private String code;//验证码
	private boolean fromCart;//是否从购物车进入
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getAddrId() {
		return addrId;
	}
	public void setAddrId(String addrId) {
		this.addrId = addrId;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isFromCart() {
		return fromCart;
	}
	public void setFromCart(boolean fromCart) {
		this.fromCart = fromCart;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
