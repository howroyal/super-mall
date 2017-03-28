/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年4月1日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.dao.OrderDetailInfoDao;
import com.mall.common.dao.OrderInfoDao;
import com.mall.common.dto.OrderInfoDto;
import com.mall.common.entity.OrderDetailInfo;
import com.mall.common.entity.OrderInfo;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年4月1日
 *@Version:1.1.0
 */
@Service
public class OrderService {

	@Resource
	private OrderInfoDao orderInfoDao;
	@Resource
	private OrderDetailInfoDao orderDetailInfoDao;
	
	/**
	 * 保存订单
	 * @Description:
	 * @param info
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年4月7日
	 */
	@Transactional
	public String save(OrderInfo info){
		Long id = orderInfoDao.add(info);
		List<OrderDetailInfo> infos = info.getDetailInfos();
		for (OrderDetailInfo detailInfo : infos) {
			detailInfo.setOrderId(String.valueOf(id));
			orderDetailInfoDao.add(detailInfo);
		}
		return info.getOrderNo();
	}
	
	/**
	 * 查询订单
	 * @Description:
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年5月26日
	 */
	public List<OrderInfo> getOrders(OrderInfoDto dto){
		return orderInfoDao.getOrders(dto);
	}
	
	/**
	 * 查询订单数量
	 * @Description:
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年5月26日
	 */
	public long getOrdersCount(OrderInfoDto dto){
		return orderInfoDao.getOrdersCount(dto);
	}
	
}
