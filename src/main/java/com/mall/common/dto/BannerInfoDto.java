/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年7月4日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dto;


/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年7月4日
 *@Version:1.1.0
 */
public class BannerInfoDto extends BaseDto{
	private Long id; //主键
	private String bannerName; //banner名称
	private Integer state;//状态
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBannerName() {
		return bannerName;
	}
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
