/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年1月12日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.ps;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.internal.StringMap;
import com.mall.common.dto.ProductDto;
import com.mall.common.entity.GoodsInfo;
import com.mall.common.service.GoodsInfoService;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年1月12日
 *@Version:1.1.0
 */
@Controller
public class ItemListController {
	@Resource
	private GoodsInfoService goodsInfoService;
	@RequestMapping("items")
	public ModelAndView index(ProductDto dto) throws Exception{
		return this.goodsInfo(null,dto);
	}
	
	@RequestMapping(value="items/{categoryId}")
	public ModelAndView goodsInfo(@PathVariable String categoryId,ProductDto dto) throws Exception{
		if (StringUtils.isEmpty(categoryId) || categoryId.equals("index")) {
			categoryId = "";
		}
		String k = dto.getK();
		if (StringUtils.isNotEmpty(k)) {
			dto.setK( new String(k.getBytes("iso8859-1"),"utf-8"));
		}
		StringMap<String> params = dto2Map(dto);
		
		if (StringUtils.isNotEmpty(categoryId)) {
			params.put("categoryIds", "(" + categoryId + ")");
		}
		List<GoodsInfo> goods = goodsInfoService.queryByParams(params);
		Long count = goodsInfoService.queryCountByParams(params);
		ModelAndView view = new ModelAndView("item_list");
		view.addObject("goodsList", goods);
		view.addObject("categoryId", categoryId);
		view.addObject("dto",dto);
		view.addObject("pagenum", (count-1)/20 + 1);
		return view;
	}
	
	private StringMap<String> dto2Map(ProductDto dto){
		StringMap<String> params = new StringMap<String>();
		String cp = dto.getCp();
		if (!StringUtils.isNumeric(cp)) {
			cp = "1";
			dto.setCp(cp);
		}
		params.put("curPage", cp);
		params.put("pageSize", "20");
		String sf = dto.getSf();
		if (StringUtils.isNotEmpty(sf)) {
			//sc 销量
			if (sf.equals("sc")) {
				sf = "sales";
			}else if(sf.equals("pc")){
				sf = "price";
			}else if(sf.equals("st")){
				sf = "create_time";
			}
			params.put("orderBy", sf);
		}
		String st = dto.getSt();
		if (StringUtils.isNotEmpty(st)) {
			if (st.equals("d")) {
				st = "DESC";
			}else if (st.equals("a")) {
				st = "ASC";
			}
			params.put("orderType", st);
		}
		
		String min = dto.getMin();
		if (StringUtils.isNumeric(min)) {
			params.put("minPrice", min);
		}
		
		String max = dto.getMax();
		if (StringUtils.isNumeric(max)) {
			params.put("maxPrice", max);
		}

		String k = dto.getK();
		if (StringUtils.isNotEmpty(k)) {
			params.put("name", k);
		}
		return params;
	} 
}
