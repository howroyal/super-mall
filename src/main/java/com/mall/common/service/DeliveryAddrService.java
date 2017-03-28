/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月28日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.dao.AreaDao;
import com.mall.common.dao.CityDao;
import com.mall.common.dao.DeliveryAddrDao;
import com.mall.common.dao.ProvinceDao;
import com.mall.common.entity.Area;
import com.mall.common.entity.City;
import com.mall.common.entity.DeliveryAddress;
import com.mall.common.entity.Province;
import com.mall.common.model.DeliveryAddressModel;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月28日
 *@Version:1.1.0
 */
@Service
public class DeliveryAddrService {

	@Resource
	private DeliveryAddrDao addrDao;
	@Resource
	private ProvinceDao provDao;
	@Resource
	private CityDao cityDao;
	@Resource
	private AreaDao areaDao;
	
	public void add(DeliveryAddress bean){
		addrDao.add(bean);
	}
	
	public void update(DeliveryAddress bean){
		addrDao.update(bean);
	}
	
	/**
	 * @Description:
	 * @param id
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年4月1日
	 */
	public void remove(Long id) {
		addrDao.remove(id);
	}
	
	public DeliveryAddress queryById(Long id){
		return addrDao.queryById(id);
	}
	
	public List<DeliveryAddress> queryBy(String memberId){
		return addrDao.queryBy(memberId);
	}
	
	public long queryCountBy(String memberId){
		return addrDao.queryCountBy(memberId);
	}
	
	public DeliveryAddressModel beanToModel(DeliveryAddress addr){

		DeliveryAddressModel model = new DeliveryAddressModel();
		model.setId(addr.getId());
		model.setMemberId(addr.getMemberId());
		model.setContactName(addr.getContactName());
		model.setProvId(addr.getProvId());
		model.setCityId(addr.getCityId());
		model.setAreaId(addr.getAreaId());
		model.setAddressDetail(addr.getAddressDetail());
		model.setContactTel(addr.getContactTel());
		model.setContactPhone(addr.getContactPhone());
		model.setPostCode(addr.getPostCode());
		model.setIsDefault(addr.getIsDefault()+"");
		model.setValid(addr.getValid());
		Province p = provDao.queryById(addr.getProvId());
		if (p!= null) {
			model.setProvName(p.getName());
		}
		
		City c = cityDao.queryById(addr.getCityId());
		if(c != null){
			model.setCityName(c.getName());
		}
		
		Area a = areaDao.queryById(addr.getAreaId());
		if(a != null){
			model.setAreaName(a.getName());
		}
		
		return model;
	}
	
	/**
	 * @Description:
	 * @param addres
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月28日
	 */
	public List<DeliveryAddressModel> beanToModel(List<DeliveryAddress> addres) {
		List<DeliveryAddressModel> models = new ArrayList<DeliveryAddressModel>();
		for (DeliveryAddress addr: addres) {
			models.add(beanToModel(addr));
		}
		return models;
	}
}
