package com.mall.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.dao.OrderDetailInfoDao;
import com.mall.common.entity.OrderDetailInfo;

@Service
public class OrderDetailInfoService {

	@Resource
	private OrderDetailInfoDao dao;
	
	public void add(OrderDetailInfo bean){
		dao.add(bean);
	}
}
