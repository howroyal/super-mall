/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月31日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.dao.CityDao;
import com.mall.common.entity.City;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月31日
 *@Version:1.1.0
 */
@Service
public class CityService {

	@Resource
	private CityDao dao;
	
	public List<City> getCitiesByPovCode(String provinceCode){
		return dao.queryCitysByProCode(provinceCode);
	}
	
}
