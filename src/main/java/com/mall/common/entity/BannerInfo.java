/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月4日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *@Title:banner信息实体类
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月4日
 *@Version:1.1.0
 */
@Entity
@Table(name = "banner_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class BannerInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "banner_info")
	@Column(name = "id")
	private Long id; //主键
	
	@Column(name = "banner_name")
	private String bannerName; //banner名称
	
	@Column(name = "pic_url")
	private String picUrl;//图片url
	
	@Column(name = "url")
	private String url;//地址url
	
	@Column(name = "description")
	private String description;//描述
	
	@Column(name = "seat")
	private int seat;//位置
	
	@Column(name = "state")
	private int state;//状态
	
	@Column(name = "create_time")
	private Date createTime;//创建时间
	
	@Column(name = "update_time")
	private Date updateTime;//更新时间
	
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
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
