/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年5月6日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.ps.my;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.dto.BalanceDto;
import com.mall.common.entity.BalanceInfo;
import com.mall.common.service.BalanceInfoService;
import com.mall.controller.BaseController;
import com.mall.interceptor.LoginRequired;

/**
 *@Title: 我的订单页面
 *@Description:
 *@Author:hao.wang
 *@Since:2016年5月6日
 *@Version:1.1.0
 */
@Controller
@RequestMapping("/my")
@LoginRequired
public class MyBalanceController extends BaseController{
	@Resource
	private BalanceInfoService balanceInfoService;

	/**
	 * @Description:
	 * @param s  订单类型 1=待支付 2=待评价 其余=所有
	 * @param i  当前第几页
	 * @param p	 每页数量
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年5月26日
	 */
	@RequestMapping("/balance")
	public ModelAndView balance(String s,String i,HttpServletRequest request){
		
		ModelAndView view = new ModelAndView("my/my_balance");
		String userId = getUserIdFormCookie(request);
		if (StringUtils.isEmpty(userId)) {
			return view;
		}
		
		BalanceDto dto = new BalanceDto();
		dto.setUserId(userId);
		
		if (!StringUtils.isEmpty(s)) {
			if (s.equals("1")) {
				dto.setType(0);
			}else if(s.equals("2")) {
				dto.setType(1);
			}else {
				s = null;
			}
		}
		
		int pageIndex = 1;
		int pageSize = 10;
		if (StringUtils.isNumeric(i)) {
			pageIndex = Integer.valueOf(i);
		}
		
		dto.setStart((pageIndex-1) * pageSize);
		dto.setEnd(pageIndex * pageSize);
		List<BalanceInfo> balances = balanceInfoService.getBalances(dto);
		long count = balanceInfoService.getBalanceCount(dto);
		float balance = balanceInfoService.getBalance(userId);
		
		view.addObject("balances", balances);
		view.addObject("count", count);
		view.addObject("s", s);
		view.addObject("i", pageIndex);
		view.addObject("pgcount",(count-1)/pageSize +1);
		view.addObject("balance",balance);
		return view;
	}
	
	
}
