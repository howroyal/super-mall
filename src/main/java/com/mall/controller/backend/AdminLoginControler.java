package com.mall.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin")
public class AdminLoginControler {

	@RequestMapping(value="doLogin")
	public String doLogin(HttpServletResponse respons,
			HttpServletRequest request, Model model) throws Exception{
		
		
		return null;
	}
	
}
