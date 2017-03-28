/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年4月15日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dto;


/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年4月15日
 *@Version:1.1.0
 */
public class GoodsDto extends BaseDto{

	private String categroyId;
	private String name;
	public String getCategroyId() {
		return categroyId;
	}
	public void setCategroyId(String categroyId) {
		this.categroyId = categroyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
