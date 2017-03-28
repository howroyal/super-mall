/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2015年12月24日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.config;

import java.util.ArrayList;
import java.util.List;

import com.mall.common.vo.GoodsInfoVO;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2015年12月24日
 *@Version:1.1.0
 */
public class MallCache {
	private static List<GoodsInfoVO> panicBuyGoodsList = new ArrayList<GoodsInfoVO>();

	/**
	 * @return the panicBuyGoodsList
	 */
	public static List<GoodsInfoVO> getPanicBuyGoodsList() {
		return panicBuyGoodsList;
	}

	/**
	 * @param panicBuyGoodsList the panicBuyGoodsList to set
	 */
	public static void setPanicBuyGoodsList(List<GoodsInfoVO> panicBuyGoodsList) {
		MallCache.panicBuyGoodsList = panicBuyGoodsList;
	}
	
	
}
