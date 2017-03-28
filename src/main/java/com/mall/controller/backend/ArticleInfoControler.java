package com.mall.controller.backend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mall.common.entity.ArticleCategoryInfo;
import com.mall.common.entity.ArticleInfo;
import com.mall.common.service.ArticleCategoryService;
import com.mall.common.service.ArticleInfoService;
import com.mall.util.ArticleCategoryVo;

@Controller
public class ArticleInfoControler {

	@Resource
	private ArticleCategoryService articleCategoryService;
	@Resource
	private ArticleInfoService articleService;
	
	@RequestMapping(value="articat")
	public String articleCategory(HttpServletRequest request,HttpServletResponse respons,Model model){
		
		String id = request.getParameter("id");
		//取得左侧的所有数据
		List<ArticleCategoryInfo> catInfos = articleCategoryService.getInfos();
		List<ArticleCategoryVo> articleCateVos = createVo(catInfos);
		
		List<ArticleInfo> articleInfos = null;
		//生成右侧数据
		if (!StringUtils.isNumeric(id)) {
			articleInfos = articleCateVos.get(0).getInfos();
		}else {
			Long idL = Long.parseLong(id);
			for (ArticleCategoryVo vo:articleCateVos) {
				if (idL.longValue() == vo.getId().longValue() ) {
					articleInfos = vo.getInfos();
				}
			}
		}
		
		model.addAttribute("articleCateVos", articleCateVos);
		model.addAttribute("articleInfos", articleInfos);
		return "article-cat";
	}
	
	@RequestMapping(value="article")
	public String article(HttpServletRequest request,HttpServletResponse respons,Model model){
		
		String id = request.getParameter("id");
		//取得左侧的所有数据
		List<ArticleCategoryInfo> catInfos = articleCategoryService.getInfos();
		List<ArticleCategoryVo> articleCateVos = createVo(catInfos);
		
		ArticleInfo info = null;
		//生成右侧数据
		if (StringUtils.isNumeric(id)) {
			Long idL = Long.parseLong(id);
			for (ArticleCategoryVo vo:articleCateVos) {
				for (ArticleInfo info2 : vo.getInfos()) {
					if (idL == info2.getId()) {
						info = info2;
					}
				}
			}
		
		}
		
		model.addAttribute("articleCateVos", articleCateVos);
		model.addAttribute("article", info);
		return "article";
	}
	
	private List<ArticleCategoryVo> createVo(List<ArticleCategoryInfo> catInfos){
		List<ArticleCategoryVo> vos = new ArrayList<ArticleCategoryVo>();
		if (catInfos == null) {
			return vos;
		}
		
		for (ArticleCategoryInfo cate : catInfos) {
			ArticleCategoryVo vo = new ArticleCategoryVo();
			Long cateId = cate.getId();
			String cateName = cate.getName();
			List<ArticleInfo> articleInfos = articleService.queryByPid(cateId);
			
			vo.setId(cateId);
			vo.setName(cateName);
			vo.setInfos(articleInfos);
			vos.add(vo);
		}
		
		return vos;
	}
}
