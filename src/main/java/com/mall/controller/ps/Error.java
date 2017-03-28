/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年4月13日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.ps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年4月13日
 *@Version:1.1.0
 */
@Controller
public class Error {

	@RequestMapping("/error")
	public String err(){
		return "mall_error";
	}
}
