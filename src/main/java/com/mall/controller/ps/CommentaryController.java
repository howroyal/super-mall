/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年4月12日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.ps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.vo.CommentVO;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年4月12日
 *@Version:1.1.0
 */
@Controller
@RequestMapping("/mallCommentary")
public class CommentaryController {

	@RequestMapping("/index")
	public ModelAndView index(String goodsId){
		ModelAndView view = new ModelAndView("commentary");
		CommentVO vo = new CommentVO();
		view.addObject("commentVoDefault", vo);
		view.addObject("commentVo", vo);
		return view;
	}
}
