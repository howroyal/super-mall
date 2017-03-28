package com.mall.controller.backend;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mall.common.entity.CategoryInfo;
import com.mall.common.service.CategoryInfoService;
import com.mall.util.PaginationUtil;

/**   
 * 
 * @ClassName  CategoryInfoControler   
 * @Description TODO   
 * @author 王浩  
 * @date   2015-8-6 下午02:49:43   
 *      
 */ 
@Controller
@RequestMapping(value="category")
public class CategoryInfoControler {
	@Resource
	private CategoryInfoService categoryInfoService;

	@RequestMapping(value="list")
	public String list(HttpServletResponse respons,
			HttpServletRequest request, Model model)throws Exception{
		respons.setCharacterEncoding("UTF-8");  
		respons.setContentType("application/json; charset=utf-8");
		//分页
		String curPage = request.getParameter("curPage");//当前页
		String pageSize = request.getParameter("pageSize");//每页多少天数据
	
		if (!StringUtils.isNumeric(curPage)) {
			curPage = "1";
		}
		
		if(!StringUtils.isNumeric(pageSize)){
			pageSize = "10";
		}
		curPage = curPage.trim();
		pageSize = pageSize.trim();
		
		int curPageInt = Integer.parseInt(curPage);
		int pageSizeInt = Integer.parseInt(pageSize);
		
		List<CategoryInfo> infos = categoryInfoService.query(curPageInt,pageSizeInt);
		
		Long count = categoryInfoService.queryCount();
		
		String pageHTML = PaginationUtil.createPageHTML(curPageInt,pageSizeInt,count);
		
		List<CategoryInfo> allInfos = categoryInfoService.query(1,1000);
		
		model.addAttribute("allInfos", allInfos);
		model.addAttribute("datas", infos);
		model.addAttribute("pageHTML",pageHTML);
		return "admin/categoryManger";
	}
	
	/**   
	 * 添加类目
	 * @author 王浩  
	 * @Since 2015-8-6
	 * @param respons
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */  
	@RequestMapping(value="addCategory")
	public String addCategory(HttpServletResponse respons,
			HttpServletRequest request) throws Exception{
		respons.setCharacterEncoding("UTF-8");  
		respons.setContentType("application/json; charset=utf-8");
		
		String name = request.getParameter("name");
		String parentId = request.getParameter("parentId");
		String imgUrl = request.getParameter("imgUrl");
		String description = request.getParameter("description");
		//检测参数
		if(StringUtils.isEmpty(name)){
			respons.getWriter().write("false");
			return null;
		}
		Long parentIdL = null;
		if (StringUtils.isNumeric(parentId)) {
			parentIdL = Long.parseLong(parentId);
		}
		
		CategoryInfo bean = new CategoryInfo();
		bean.setName(name);
		bean.setParentId(parentIdL);
		bean.setImgUrl(imgUrl);
		bean.setDescription(description);
		
		categoryInfoService.addCategory(bean);
		respons.getWriter().write("true");
		return null;
	}
	
	@RequestMapping(value="deleteCategory")
	public String deleteCategory(HttpServletResponse respons,
			HttpServletRequest request) throws Exception{
		respons.setCharacterEncoding("UTF-8");  
		respons.setContentType("application/json; charset=utf-8");
		
		String id = request.getParameter("id");
		
		if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			respons.getWriter().write("false");
			return null;
		}
		
		categoryInfoService.delete(Long.parseLong(id));
		respons.getWriter().write("true");
		return null;
	}
	@RequestMapping("updateCategory")
	public String updateCategory(HttpServletResponse respons,
			HttpServletRequest request) throws Exception{
		respons.setCharacterEncoding("UTF-8");  
		respons.setContentType("application/json; charset=utf-8");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String parentId = request.getParameter("parentId");
		String imgUrl = request.getParameter("imgUrl");
		String description = request.getParameter("description");
		
		if (!StringUtils.isNumeric(id)) {
			respons.getWriter().write("false");
			return null;
		}
		if (StringUtils.isEmpty(name)) {
			respons.getWriter().write("false");
			return null;
		}
		Long parentIdL = null;
		if (!StringUtils.isEmpty(parentId)) {
			if (!StringUtils.isNumeric(parentId)) {
				parentIdL = null;
			}else {
				parentIdL = Long.parseLong(parentId);
			}
		}
		
		CategoryInfo info = new CategoryInfo();
		info.setId(Long.parseLong(id));
		info.setName(name);
		info.setParentId(parentIdL);
		info.setImgUrl(imgUrl);
		info.setDescription(description);
		
		categoryInfoService.update(info);
		respons.getWriter().write("true");
		
		return null;
	}
}
