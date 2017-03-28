package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.dao.CarouselInfoDao;
import com.mall.common.entity.CarouselInfo;

@Service
public class CarouselInfoService {

	@Resource
	private CarouselInfoDao carouselInfoDao;
	
	public void add(CarouselInfo bean){
		carouselInfoDao.add(bean);
	}
	
	public void update(CarouselInfo bean){
		carouselInfoDao.update(bean);
	}
	
	public List<CarouselInfo> query(int curPage,int pageSize){
		return carouselInfoDao.query(curPage, pageSize);
	}
}
