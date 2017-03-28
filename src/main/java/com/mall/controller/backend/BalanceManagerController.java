/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年5月30日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.dto.BalanceDto;
import com.mall.common.entity.BalanceInfo;
import com.mall.common.service.BalanceInfoService;

/**
 *@Title: 用户充值管理
 *@Description:
 *@Author:hao.wang
 *@Since:2016年5月30日
 *@Version:1.1.0
 */
@Controller
@RequestMapping("balanceManager")
public class BalanceManagerController {

	@Resource
	private BalanceInfoService balanceInfoService;
	
	@RequestMapping("index")
	public String index() throws Exception{
		return "admin/finance/balanceManager";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list(BalanceDto dto){
		
		List<BalanceInfo> infos = balanceInfoService.getBalances(dto);
		long total = balanceInfoService.getBalanceCount(dto);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", infos);
		result.put("success", true);
		return result;
	}
	
	@RequestMapping("remove")
	@ResponseBody
	public Map<String, Object> remove(Long id){
		Map<String, Object> result = new HashMap<String, Object>();
		
		BalanceInfo info = balanceInfoService.getById(id);
		if (info != null) {
			info.setIsValid(1);
			balanceInfoService.update(info);
		}
		
		result.put("success", true);
		return result;
	}
}
