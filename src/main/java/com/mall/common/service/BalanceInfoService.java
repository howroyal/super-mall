/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年6月3日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.dao.BalanceInfoDao;
import com.mall.common.dto.BalanceDto;
import com.mall.common.entity.BalanceInfo;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年6月3日
 *@Version:1.1.0
 */
@Service
public class BalanceInfoService {

	@Resource
	private BalanceInfoDao balanceInfoDao;
	
	public Long add(BalanceInfo bean){
		return balanceInfoDao.add(bean);
	}
	
	public BalanceInfo getById(Long id){
		return balanceInfoDao.getById(id);
	}
	
	public List<BalanceInfo> getBalances(BalanceDto dto){
		return balanceInfoDao.getBalances(dto);
	}
	
	public long getBalanceCount(BalanceDto dto){
		return balanceInfoDao.getBalanceCount(dto);
	}
	
	/**
	 * 得到用户余额
	 * @Description:
	 * @param userId
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月3日
	 */
	public float getBalance(String userId){
		return balanceInfoDao.getBalance(userId);
	}

	/**
	 * @Description:
	 * @param info
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月22日
	 */
	public void update(BalanceInfo info) {
		balanceInfoDao.update(info);
	}
}
