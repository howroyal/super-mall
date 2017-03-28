package com.mall.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**   
 * 用户信息
 * @ClassName  UserInfo   
 * @Description TODO   
 * @author 王浩  
 * @date   2015-9-30 上午11:05:33   
 *      
 */  

@Entity
@Table(name = "user_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class UserInfo {

	@Id
	@SequenceGenerator(name = "user_info")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "user_name")
	private String userName;//手机或email
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "update_time")
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
