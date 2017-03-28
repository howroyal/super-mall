/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年4月14日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.interceptor.LoginRequired;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年4月14日
 *@Version:1.1.0
 */
@Controller
@LoginRequired
@RequestMapping("manager")
public class ManagerController {

	@RequestMapping("index")
	public ModelAndView index(ModelAndView view){
		view.setViewName("admin/index");
		return view; 
	}

}
