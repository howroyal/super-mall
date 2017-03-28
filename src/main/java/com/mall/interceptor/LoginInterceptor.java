package com.mall.interceptor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mall.config.CommonVariables;
import com.mall.util.CookieUtil;
import com.mall.util.CryptUtil;



/**   
 * 登录验证拦截器
 * @ClassName  LoginInterceptor   
 * @Description TODO   
 * @author 王浩  
 * @date   2015-9-29 下午05:31:20   
 *      
 */  
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static Map<Object, Object[]> loginRequiredMap = new ConcurrentHashMap<Object, Object[]>();

	/**
	 * 在Controller方法前进行拦截
	 */
	public boolean preHandle(final HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;

			// 登录判断
			LoginRequired loginRequired = this.getLoginRequired(handlerMethod.getBeanType(), handlerMethod.getMethod());
			if (loginRequired == null) {
				return true;
			}
			
			if (loginRequired != null) {
				//response.sendRedirect("login.html?returl=" + backUrlParam);
				String memId = null;
				String memIdMd5 = CookieUtil.getCookieValue(request, CommonVariables.USERAUTH);
				if (StringUtils.isNotEmpty(memIdMd5)) {
					memId = CryptUtil.decrypt(memIdMd5);
				}
				
				if (StringUtils.isEmpty(memId)) {
					response.sendRedirect(request.getContextPath() + "/new/login.html?returl=" + request.getRequestURL());
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 在Controller方法处理中进行拦截
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在Controller方法后进行拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private LoginRequired getLoginRequired(Class<?> clazz, Method method) {
		LoginRequired loginRequired = null;

		Object[] objs = loginRequiredMap.get(method);
		if (objs == null) {
			objs = loginRequiredMap.get(clazz);
		}

		if (objs == null) {
			Boolean hasMethodLoginRequired = Boolean.FALSE;
			LoginRequired methodLoginRequired = method.getAnnotation(LoginRequired.class);
			if (methodLoginRequired != null) {
				hasMethodLoginRequired = Boolean.TRUE;
			}
			objs = new Object[] { hasMethodLoginRequired, methodLoginRequired };
			loginRequiredMap.put(method, objs);

			if (!hasMethodLoginRequired) {
				Boolean hasClassLoginRequired = Boolean.FALSE;
				LoginRequired classLoginRequired = clazz.getAnnotation(LoginRequired.class);
				if (classLoginRequired != null) {
					hasClassLoginRequired = Boolean.TRUE;
				}
				objs = new Object[] { hasClassLoginRequired, classLoginRequired };
				loginRequiredMap.put(clazz, objs);
			}
		}

		Boolean hasLoginRequired = (Boolean) objs[0];
		if (hasLoginRequired) {
			loginRequired = (LoginRequired) objs[1];
		}

		return loginRequired;
	}
}
