/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2015年12月30日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.dao.PanicBuyGoodsInfoDao;
import com.mall.common.entity.PanicBuyGoodsInfo;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2015年12月30日
 *@Version:1.1.0
 */
@Service
public class PanicBuyGoodsInfoService {

	@Resource
	private PanicBuyGoodsInfoDao dao;
	
	/**
	 * 获取抢购商品信息
	 * @Description:
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月30日
	 */
	public List<PanicBuyGoodsInfo> getInfos(){
		return dao.getInfos();
	}
}
