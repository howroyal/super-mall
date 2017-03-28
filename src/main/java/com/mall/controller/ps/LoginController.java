package com.mall.controller.ps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.entity.UserInfo;
import com.mall.common.service.UserInfoService;
import com.mall.config.CommonVariables;
import com.mall.controller.BaseController;
import com.mall.util.CookieUtil;
import com.mall.util.CryptUtil;


/**
 * 注册与登录
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年7月22日
 *@Version:1.1.0
 */
@Controller
@RequestMapping("new")
public class LoginController extends BaseController{
	
	@Resource
	private UserInfoService userInfoService;
	
	/**
	 * 进入登录页面
	 * @Description:
	 * @param backurl
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年7月22日
	 */
	@RequestMapping("/login")
	public ModelAndView login(String returl){
		ModelAndView view = new ModelAndView("new/login");
		view.addObject("returl", returl);
		return view;
	}
	
	/**
	 * 进入注册页面
	 * @Description:
	 * @param backurl
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年7月22日
	 */
	@RequestMapping("/register")
	public ModelAndView register(String backurl){
		ModelAndView view = new ModelAndView("new/reg");
		view.addObject("backurl", backurl);
		return view;
	}
	
	/**
	 * 检查账号是否登录
	 * @Description:
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年7月22日
	 */
	@RequestMapping("/check")
	@ResponseBody
	public Map<String, Object> check(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		boolean isSucc = false;
		String userName = null;
		String userId = getUserIdFormCookie(request);
		
		if (StringUtils.isNotEmpty(userId)) {
			UserInfo info = userInfoService.findById(Long.parseLong(userId));
			userName = info.getUserName();
			isSucc = true;
		}
	
		result.put("success", isSucc);
		result.put("info", userName);
		return result;
	}
	
	/**登录账号
	 * @Description:
	 * @param request
	 * @param respons
	 * @param info
	 * @param validatecode
	 * @param returl
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年7月22日
	 */
	@RequestMapping("/dologin")
	@ResponseBody
	public Map<String, Object> doLogin(HttpServletRequest request,HttpServletResponse respons, UserInfo info,String validatecode,String returl){
		Map<String, Object> result = new HashMap<String, Object>();
		boolean isSucc = false;
		int code = -1;
		result.put("code", code);
		result.put("success", isSucc);
		result.put("returl", returl);
		//检查验证码
		String sessionCode = (String)request.getSession().getAttribute("validatecode");
		boolean isCodeChecked = checkeCode(sessionCode,validatecode);
		if (!isCodeChecked) {
			return result;
		}

		//加密
		info.setPassword(CryptUtil.encrypt(info.getPassword()));
		
		List<UserInfo> infos = userInfoService.query(info);
		if (infos == null || infos.isEmpty()) {
			result.put("code", -2);
			return result;
		}
		
		isSucc = true;
		UserInfo userInfo = infos.get(0);
		//保存用户名
		CookieUtil.setCookie(request, respons, CommonVariables.USERAUTH, CryptUtil.encrypt(userInfo.getId().toString()));
	
		result.put("success", isSucc);
	
		return result;
	}
	
	/**
	 * 注册账号
	 * @Description:
	 * @param respons
	 * @param request
	 * @param info
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年7月22日
	 */
	@RequestMapping("/doreg")
	@ResponseBody
	public Map<String, Object> doReg(HttpServletResponse respons,HttpServletRequest request, UserInfo info,String validatecode){
		Map<String, Object> result = new HashMap<String, Object>();
	
		boolean isSucc = false;
		int code = -1;
		if (StringUtils.isEmpty(info.getUserName()) || StringUtils.isEmpty(info.getPassword())) {
			result.put("success", isSucc);
			result.put("code", code);
			return result;
		}

		//检查验证码
		String sessionCode = (String)request.getSession().getAttribute("validatecode");
		boolean isCodeChecked = checkeCode(sessionCode,validatecode);
		if (!isCodeChecked) {

			result.put("success", isSucc);
			result.put("code", -2);
			return result;
		}
		//检查用户名是否存在
		UserInfo info2 = new UserInfo();
		info2.setUserName(info.getUserName());
		List<UserInfo> userInfos = userInfoService.query(info);
		if (CollectionUtils.isNotEmpty(userInfos)) {
			code = -2;
			result.put("success", isSucc);
			result.put("code", code);
			return result;
		}
		//加密
		info.setPassword(CryptUtil.encrypt(info.getPassword()));
		Long id = userInfoService.add(info);
		if (id >= 0 ) {
			isSucc = true;
			//保存用户名
			CookieUtil.setCookie(request, respons, CommonVariables.USERAUTH, CryptUtil.encrypt(id.toString()));
		}

		result.put("code", 0);
		result.put("success", isSucc);
		return result;
	}
	
	/**
	 * 登出
	 * @Description:
	 * @param respons
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年7月22日
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String, Object> logout(HttpServletResponse respons,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		//保存用户名
		CookieUtil.setCookie(request, respons, CommonVariables.USERAUTH, null);
		result.put("success", true);
		return result;
	}
	
	/**
	 * @Description: 检查sessionCode和validatecode是否一致
	 * @param sessionCode
	 * @param validatecode
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年7月18日
	 */
	private boolean checkeCode(String sessionCode,String validatecode){
		if (StringUtils.isEmpty(sessionCode) || StringUtils.isEmpty(validatecode)) {
			return false;
		}
		if (!sessionCode.equalsIgnoreCase(validatecode)) {
			return false;
		}
		
		return true;
	}
	

}
