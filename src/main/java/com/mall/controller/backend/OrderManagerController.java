/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年5月30日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.dto.OrderInfoDto;
import com.mall.common.entity.OrderInfo;
import com.mall.common.service.OrderService;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年5月30日
 *@Version:1.1.0
 */
@Controller
@RequestMapping("orderManager")
public class OrderManagerController {

	@Resource
	private OrderService orderService;
	
	@RequestMapping("index")
	public String index() throws Exception{
		return "admin/order/orderManager";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list(OrderInfoDto dto){
		
		List<OrderInfo> infos = orderService.getOrders(dto);
		long total = orderService.getOrdersCount(dto);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", infos);
		result.put("success", true);
		return result;
	}
}
