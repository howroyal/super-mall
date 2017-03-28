package com.mall.controller.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.internal.StringMap;
import com.mall.common.dto.GoodsDto;
import com.mall.common.entity.GoodsInfo;
import com.mall.common.service.CategoryInfoService;
import com.mall.common.service.GoodsInfoService;

/**
 * 
 * @ClassName  GoodsInfoControler   
 * @Description TODO   
 * @author 王浩  
 * @date   2015-8-11 下午03:43:29   
 *
 */
@Controller
@RequestMapping("goodsManager")
public class GoodsInfoManagerController {

	@Resource
	private GoodsInfoService goodsInfoService;
	@Resource
	private CategoryInfoService categoryInfoService;
	
	@RequestMapping("index")
	public String index() throws Exception{
		return "admin/goods/goodsManager";
	}
	
	@RequestMapping("/new")
	public String goodsNew() throws Exception{
		return "admin/goods/goods_new";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list(GoodsDto dto){
		StringMap<String> params = new StringMap<String>();
		params.put("start", dto.getStart()+"");
		params.put("end", dto.getEnd()+"");
		if(dto.getName()!=null){
			params.put("name", dto.getName());
		}
		if (dto.getCategroyId()!=null) {
			params.put("categoryIds", dto.getCategroyId());
		}
		List<GoodsInfo> infos = goodsInfoService.queryByParams(params);
		long total = goodsInfoService.queryCountByParams(params);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", infos);
		result.put("success", true);
		return result;
	}
	
	
	@RequestMapping(value="addGoodsInfo")
	@ResponseBody
	public Map<String, Object> addGoodsInfo(GoodsInfo goodsInfo) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		goodsInfo.setCode(UUID.randomUUID().toString());
		goodsInfoService.add(goodsInfo);
		result.put("success", true);
		return result;
	}
	
	@RequestMapping(value="updateGoodsInfo")
	@ResponseBody
	public Map<String, Object> updateGoodsInfo(GoodsInfo info) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		goodsInfoService.update(info);
		result.put("success", true);
		return result;
	}
}
