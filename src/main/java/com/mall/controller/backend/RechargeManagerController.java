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

import com.mall.common.dto.RechargeDto;
import com.mall.common.entity.RechargeInfo;
import com.mall.common.service.RechargeInfoService;

/**
 *@Title: 用户充值管理
 *@Description:
 *@Author:hao.wang
 *@Since:2016年5月30日
 *@Version:1.1.0
 */
@Controller
@RequestMapping("rechargeManager")
public class RechargeManagerController {

	@Resource
	private RechargeInfoService rechargeService;
	
	@RequestMapping("index")
	public String index() throws Exception{
		return "admin/finance/rechargeManager";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list(RechargeDto dto){
		
		List<RechargeInfo> infos = rechargeService.getRecharges(dto);
		long total = rechargeService.getRechargeCount(dto);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", infos);
		result.put("success", true);
		return result;
	}
	
	/**
	 * 业务逻辑
	 * 1.确定此次充值记录有效
	 * 2.在余额里面添加此次余额
	 * @Description:
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月22日
	 */
	@RequestMapping("valid")
	@ResponseBody
	public Map<String, Object> valid(Long id, Float money){
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (id == null || money == null) {
			result.put("success", false);
			return result;
		}
		rechargeService.valid(id,money);
		
		result.put("success", true);
		return result;
	}
	
	@RequestMapping("remove")
	@ResponseBody
	public Map<String, Object> remove(Long id){
		Map<String, Object> result = new HashMap<String, Object>();
		
		RechargeInfo info = rechargeService.getById(id);
		if (info != null) {
			info.setValid(2);
			rechargeService.updat(info);
		}
		
		result.put("success", true);
		return result;
	}
}
