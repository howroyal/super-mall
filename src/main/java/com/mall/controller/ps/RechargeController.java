/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年6月6日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.ps;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.entity.RechargeInfo;
import com.mall.common.service.RechargeInfoService;
import com.mall.controller.BaseController;

/**
 *@Title: 账号充值
 *@Description:
 *@Author:hao.wang
 *@Since:2016年6月6日
 *@Version:1.1.0
 */
@Controller
@RequestMapping("recharge")
public class RechargeController extends BaseController{

	@Resource
	private RechargeInfoService rechargeInfoService;
	/**
	 * 充值
	 * @Description:
	 * @param info
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月6日
	 */
	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(RechargeInfo info,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = this.getUserIdFormCookie(request);
		if (StringUtils.isEmpty(userId)) {
			result.put("success", false);
			result.put("code", -1);
			return result;
		}
		
		if (StringUtils.isEmpty(info.getPayAccount())
			|| StringUtils.isEmpty(info.getPayInfo())
			|| StringUtils.isEmpty(info.getPayWay())
			|| StringUtils.isEmpty(info.getRechargeMoney())) {
			result.put("success", false);
			result.put("code", -2);
			return result;
		}
		
		info.setUserId(Long.valueOf(userId));
		Long id = rechargeInfoService.add(info);
		if (id != null) {
			result.put("success", true);
			result.put("code", 0);
		}
		
		return result;
	}
}
