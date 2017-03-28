/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年6月6日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.dao.BalanceInfoDao;
import com.mall.common.dao.RechargeInfoDao;
import com.mall.common.dto.RechargeDto;
import com.mall.common.entity.BalanceInfo;
import com.mall.common.entity.RechargeInfo;

/**
 *@Title:用户充值记录
 *@Description:
 *@Author:hao.wang
 *@Since:2016年6月6日
 *@Version:1.1.0
 */
@Service
public class RechargeInfoService {

	@Resource
	private RechargeInfoDao rechargeInfoDao;
	@Resource
	private BalanceInfoDao balanceInfoDao;
	/**
	 * 添加充值
	 * @Description:
	 * @param info
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月21日
	 */
	public Long add(RechargeInfo info){
		return rechargeInfoDao.add(info);
	}
	
	/**
	 * 确认充值
	 * @Description:
	 * @param id
	 * @param money
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月22日
	 */
	public void valid(Long id, Float money){
		RechargeInfo info = rechargeInfoDao.getById(id);
		if (info != null) {
			info.setValid(1);
			rechargeInfoDao.update(info);
			
			BalanceInfo bInfo = new BalanceInfo();
			bInfo.setMoney(money);
			bInfo.setUserId(info.getUserId());
			balanceInfoDao.add(bInfo);
		}
	}
	
	/**
	 * @Description:
	 * @param id
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月21日
	 */
	public RechargeInfo getById(Long id){
		return rechargeInfoDao.getById(id);
	}
	
	public int updat(RechargeInfo info){
		return rechargeInfoDao.update(info);
	}
	
	public List<RechargeInfo> getRecharges(RechargeDto dto){
		return rechargeInfoDao.getRecharges(dto);
	}
	
	public long getRechargeCount(RechargeDto dto){
		return rechargeInfoDao.getRechargeCount(dto);
	}
	
}
