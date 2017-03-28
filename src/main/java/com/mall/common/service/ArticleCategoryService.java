package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.dao.ArticleCategoryDao;
import com.mall.common.entity.ArticleCategoryInfo;

@Service
public class ArticleCategoryService {

	@Resource
	private ArticleCategoryDao artCatDao;
	
	public List<ArticleCategoryInfo> getInfos(){
		
		return artCatDao.getInfos();
	}
}
