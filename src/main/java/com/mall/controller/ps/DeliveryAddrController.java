/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月31日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.ps;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.entity.Area;
import com.mall.common.entity.City;
import com.mall.common.entity.DeliveryAddress;
import com.mall.common.entity.Province;
import com.mall.common.model.DeliveryAddressModel;
import com.mall.common.service.AreaService;
import com.mall.common.service.CityService;
import com.mall.common.service.DeliveryAddrService;
import com.mall.common.service.ProvinceService;
import com.mall.common.vo.JsonVo;
import com.mall.config.CommonVariables;
import com.mall.util.CookieUtil;
import com.mall.util.CryptUtil;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月31日
 *@Version:1.1.0
 */
@Controller
public class DeliveryAddrController {
	/**
	 * @Description:
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月31日
	 */
	@Resource
	private DeliveryAddrService addrService;
	@Resource
	private ProvinceService provinceService;
	@Resource
	private CityService cityService;
	@Resource
	private AreaService areaService;
	
	
	@RequestMapping("/addr/provinces")
	@ResponseBody
	public List<Province> provinces(){
		return provinceService.getProvinces();
	}
	
	@RequestMapping("/addr/cities")
	@ResponseBody
	public List<City> cities(String provCode){
		return cityService.getCitiesByPovCode(provCode);
	}
	
	@RequestMapping("/addr/areas")
	@ResponseBody
	public List<Area> areas(String cityCode){
		return areaService.getAreasByCityCode(cityCode);
	}
	
	/**
	 * 新增用户收货地址
	 * @Description:
	 * @param dto
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月16日
	 */
	@RequestMapping("/addr/newAddr")
	@ResponseBody
	public JsonVo<Object> newAddr(DeliveryAddress bean,HttpServletRequest request){
		String memId = null;
		String memIdMd5 = CookieUtil.getCookieValue(request, CommonVariables.USERAUTH);
		if (memIdMd5 != null) {
			memId = CryptUtil.decrypt(memIdMd5);
		}
		if (StringUtils.isEmpty(memId)) {
			return new JsonVo<Object>(false,"-2",null);
			
		}
		JsonVo<Object> result = new JsonVo<Object>(false,"-1",null);
		if (bean == null) {
			return result;
		}
		try {
			bean.setMemberId(memId);
			addrService.add(bean);
			result = new JsonVo<Object>(true,"0",bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 取到用户收货地址
	 * @Description:
	 * @param pkid
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月16日
	 */
	@RequestMapping("/addr")
	@ResponseBody
	public JsonVo<Object> addr(Long id,HttpServletRequest request) {
		String memId = null;
		String memIdMd5 = CookieUtil.getCookieValue(request, CommonVariables.USERAUTH);
		if (memIdMd5 != null) {
			memId = CryptUtil.decrypt(memIdMd5);
		}
		if (StringUtils.isEmpty(memId)) {
			return new JsonVo<Object>(false,"-2",null);
			
		}
		JsonVo<Object> result = new JsonVo<Object>(false,"-1",null);
		DeliveryAddressModel  model = null;
		try {
			model = addrService.beanToModel(addrService.queryById(id));
			if (model != null && model.getValid() == 0) {
				if (model.getMemberId().equals(memId)) {
					result = new JsonVo<Object>(true,"0",model);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 更新用户收货地址
	 * @Description:
	 * @param pkid
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月16日
	 */
	@RequestMapping("/addr/updateAddr")
	@ResponseBody
	public JsonVo<Object> updateAddr(DeliveryAddress bean,HttpServletRequest request) {
		
		String memId = null;
		String memIdMd5 = CookieUtil.getCookieValue(request, CommonVariables.USERAUTH);
		if (memIdMd5 != null) {
			memId = CryptUtil.decrypt(memIdMd5);
		}
		if (StringUtils.isEmpty(memId)) {
			return new JsonVo<Object>(false,"-2",null);
		}
		
		JsonVo<Object> result = new JsonVo<Object>(false,"-1",null);
		try {
			Long id = bean.getId();
			if (id == null) {
				return result;
			}
			DeliveryAddress address = addrService.queryById(id);
			if (address == null || !memId.equals(address.getMemberId())) {
				return result;
			}
			bean.setMemberId(memId);
			bean.setValid(0);
			addrService.update(bean);
			result = new JsonVo<Object>(true,"0",bean);;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 删除用户收货地址
	 * @Description:
	 * @param pkid
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月16日
	 */
	@RequestMapping("/addr/removeAddr")
	@ResponseBody
	public JsonVo<Object> removeAddr(Long id,HttpServletRequest request) {
		
		JsonVo<Object> result = new JsonVo<Object>(false,"-1",null);
		String memId = null;
		String memIdMd5 = CookieUtil.getCookieValue(request, CommonVariables.USERAUTH);
		if (memIdMd5 != null) {
			memId = CryptUtil.decrypt(memIdMd5);
		}
		if (StringUtils.isEmpty(memId)) {
			return result;
		}
		
		try {
			DeliveryAddress model = addrService.queryById(id);
			if (model == null || !memId.equals(model.getMemberId())) {
				return result;
			}
			
			addrService.remove(id);
			result = new JsonVo<Object>(true,"0",null);;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 设置默认用户收货地址
	 * @Description:
	 * @param pkid
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月16日
	 */
	@RequestMapping("/addr/setAddrIsDefault")
	@ResponseBody
	public JsonVo<Object> setAddrIsDefault(Long id,HttpServletRequest request) {
		JsonVo<Object> result = new JsonVo<Object>(false,"-1",null);
		
		String memId = null;
		String memIdMd5 = CookieUtil.getCookieValue(request, CommonVariables.USERAUTH);
		if (memIdMd5 != null) {
			memId = CryptUtil.decrypt(memIdMd5);
		}
		if (StringUtils.isEmpty(memId)) {
			return result;
		}
		
		try {

			List<DeliveryAddress> addresses = addrService.queryBy(memId);
			for (DeliveryAddress deliveryAddress : addresses) {
				if (deliveryAddress.getIsDefault() == 1) {
					deliveryAddress.setIsDefault(0);
				}
				if (id == deliveryAddress.getId()) {
					deliveryAddress.setIsDefault(1);
				}
				
				addrService.update(deliveryAddress);
			}
			result = new JsonVo<Object>(true,"0",null);;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
