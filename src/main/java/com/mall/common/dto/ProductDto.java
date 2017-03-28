/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月22日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dto;


/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月22日
 *@Version:1.1.0
 */
public class ProductDto {
	private String sf;//排名排序字段
	private String st;//升序降序
	private String cp;//当前页
	private String pn;//查询商品名
	private String min;//商品最低价
	private String max;//商品最高价
	private String k;//关键字查询
	public String getSf() {
		return sf;
	}
	public void setSf(String sf) {
		this.sf = sf;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
}
