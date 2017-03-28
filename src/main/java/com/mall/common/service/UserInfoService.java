package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.internal.StringMap;
import com.mall.common.dao.UserInfoDao;
import com.mall.common.entity.UserInfo;


/**
 * 用户信息的service
 * 
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年7月25日
 *@Version:1.1.0
 */
@Service
public class UserInfoService {
	@Resource
	private UserInfoDao userInfoDao;
	
	public Long add(UserInfo bean){
		return userInfoDao.add(bean);
	}
	
	public UserInfo findById(Long id){
		return userInfoDao.queryById(id);
	}
	
	public List<UserInfo> query(UserInfo info){
		return userInfoDao.query(info);
	}
	
	public void update(UserInfo bean){
		userInfoDao.update(bean);
	}
}
