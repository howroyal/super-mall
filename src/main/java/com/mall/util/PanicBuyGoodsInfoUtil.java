/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2015年12月30日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.util;

import java.util.ArrayList;
import java.util.List;

import com.mall.common.entity.GoodsInfo;
import com.mall.common.entity.PanicBuyGoodsInfo;
import com.mall.common.service.GoodsInfoService;
import com.mall.common.vo.GoodsInfoVO;

/**
 * 抢购的工具类
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2015年12月30日
 *@Version:1.1.0
 */
public class PanicBuyGoodsInfoUtil {

	/**
	 *  bean转换vo
	 * @Description:
	 * @param goodsInfoService
	 * @param paincBuyGoodsInfos
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月31日
	 */
	public static List<GoodsInfoVO> infoToVo(GoodsInfoService goodsInfoService, List<PanicBuyGoodsInfo> paincBuyGoodsInfos){
		List<GoodsInfoVO> goodsInfoVOs = new ArrayList<GoodsInfoVO>();
		for (PanicBuyGoodsInfo info : paincBuyGoodsInfos) {
			String goodsId = info.getGoodsId();
			GoodsInfo goodsInfo = goodsInfoService.queryById(Long.parseLong(goodsId));
			if (goodsInfo != null) {
				GoodsInfoVO vo = new GoodsInfoVO();
				vo.setId(goodsInfo.getId());
				vo.setName(goodsInfo.getName());
				vo.setPrice(info.getPrice());
				
				goodsInfoVOs.add(vo);
			}
		}
		
		return goodsInfoVOs;
	}
}
