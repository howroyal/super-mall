package com.mall.controller.ps;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.internal.StringMap;
import com.mall.common.dto.BannerInfoDto;
import com.mall.common.entity.BannerInfo;
import com.mall.common.entity.GoodsInfo;
import com.mall.common.entity.PanicBuyGoodsInfo;
import com.mall.common.service.BannerInfoService;
import com.mall.common.service.GoodsInfoService;
import com.mall.common.service.PanicBuyGoodsInfoService;
import com.mall.common.vo.GoodsInfoVO;
import com.mall.config.MallCache;
import com.mall.util.PanicBuyGoodsInfoUtil;

/**   
 * 首页
 * @ClassName  IndexControler   
 * @Description TODO   
 * @author 王浩  
 * @date   2015-8-5 下午07:02:19   
 */  
@Controller
public class IndexController{
	
	//抢购service
	@Resource
	private PanicBuyGoodsInfoService paincBuyGoodsInfoService;
	//商品的service
	@Resource
	private GoodsInfoService goodsInfoService;
	
	@Resource
	private BannerInfoService bannerInfoService;
	/**   
	 * 直进首页
	 * @author 王浩  
	 * @Since 2015-8-5
	 * @param respons
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */  
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request) throws Exception{
		ModelAndView model = new ModelAndView("index");
		//抢购
		List<GoodsInfoVO> panicBuyGoodsList = MallCache.getPanicBuyGoodsList();
		if (panicBuyGoodsList == null || panicBuyGoodsList.isEmpty()) {
			List<PanicBuyGoodsInfo> infos = paincBuyGoodsInfoService.getInfos();
			if (infos != null) {
				panicBuyGoodsList = PanicBuyGoodsInfoUtil.infoToVo(goodsInfoService, infos);
				if (panicBuyGoodsList != null) {
					MallCache.setPanicBuyGoodsList(panicBuyGoodsList);
				}
			}
		}
		//换一换
		List<GoodsInfo> guesGoods = goodsInfoService.query(1, 6);
		
		
		StringMap<String> params = new StringMap<String>();
		
		params.put("curPage", "1");
		params.put("pageSize", "5");
		//家居服饰
		params.put("categoryIds", "(1)");
		List<GoodsInfo> goods1 = goodsInfoService.queryByParams(params);
		//数码电器
		params.put("categoryIds", "(2)");
		List<GoodsInfo> goods2 = goodsInfoService.queryByParams(params);
		//母婴玩具
		params.put("categoryIds", "(3)");
		List<GoodsInfo> goods3 = goodsInfoService.queryByParams(params);
		//食品保健
		params.put("categoryIds", "(4)");
		List<GoodsInfo> goods4 = goodsInfoService.queryByParams(params);
		//美容配饰
		params.put("categoryIds", "(5)");
		List<GoodsInfo> goods5 = goodsInfoService.queryByParams(params);
		//汽车运动
		params.put("categoryIds", "(6)");
		List<GoodsInfo> goods6 = goodsInfoService.queryByParams(params);
		
		//首页banner
		BannerInfoDto dto = new BannerInfoDto();
		dto.setState(0);
		
		List<BannerInfo> bannerInfos = bannerInfoService.query(dto);
		//新品推荐
		params.remove("categoryIds");
		params.put("curPage", "1");
		params.put("pageSize", "20");
		params.put("orderBy", "create_time");
		params.put("orderType", "DESC");
		List<GoodsInfo> jptjGoods = goodsInfoService.queryByParams(params);
		model.addObject("panicBuyGoodsList", jptjGoods);
		model.addObject("jptjGoods", jptjGoods);
		model.addObject("guesGoods", guesGoods);
		model.addObject("goods1", goods1);
		model.addObject("goods2", goods2);
		model.addObject("goods3", goods3);
		model.addObject("goods4", goods4);
		model.addObject("goods5", goods5);
		model.addObject("goods6", goods6);
		model.addObject("bannerInfos", bannerInfos);
		return model;
	}
	
	/**
	 * @Description:
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月18日
	 */
	@RequestMapping("changeitems")
	@ResponseBody
	public Map<String, Object> changeItmes(int curPage){
		List<GoodsInfo> guesGoods = goodsInfoService.query(curPage, 6);
		if (guesGoods == null || guesGoods.isEmpty()) {
			curPage = 1 ;
			 guesGoods = goodsInfoService.query(curPage, 6);
		}
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("code", 0);
		vo.put("status", true);
		vo.put("curPage", curPage);
		vo.put("data", guesGoods);
		return vo;
	}
}
