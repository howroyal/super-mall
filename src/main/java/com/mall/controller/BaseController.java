/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年5月26日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.mall.config.CommonVariables;
import com.mall.util.CookieUtil;
import com.mall.util.CryptUtil;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年5月26日
 *@Version:1.1.0
 */
public class BaseController {

	/** 
	 * 从cookie中获取用户id
	 * @Description:
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年5月26日
	 */
	public String getUserIdFormCookie(HttpServletRequest request){
		String memId = null;
		String memIdMd5 = CookieUtil.getCookieValue(request, CommonVariables.USERAUTH);
		if (StringUtils.isNotEmpty(memIdMd5)) {
			memId = CryptUtil.decrypt(memIdMd5);
		}
		return memId;
	}
}
