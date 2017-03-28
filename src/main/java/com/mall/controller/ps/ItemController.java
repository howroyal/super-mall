package com.mall.controller.ps;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.entity.GoodsInfo;
import com.mall.common.service.CategoryInfoService;
import com.mall.common.service.GoodsInfoService;
import com.mall.common.vo.GoodsInfoVO;
import com.mall.util.GoodsInfoUtil;

@Controller
@RequestMapping("item")
public class ItemController {
	@Resource
	private GoodsInfoService goodsInfoService;
	@Resource
	private CategoryInfoService categoryInfoService;
	
	@RequestMapping(value="{goodsId}")
	public ModelAndView goodsInfo(@PathVariable String goodsId) throws Exception{
		if (StringUtils.isEmpty(goodsId) || goodsId.equals("index")) {
			goodsId = "1";
		}
		
		GoodsInfo info = goodsInfoService.queryById(Long.parseLong(goodsId));
		GoodsInfoVO vo = GoodsInfoUtil.infoToVo(categoryInfoService, info);
		ModelAndView view = new ModelAndView("item_info");
		view.addObject("goods", vo);
		return view;
	}
}
