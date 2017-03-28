package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.dao.ArticleInfoDao;
import com.mall.common.entity.ArticleInfo;

@Service
public class ArticleInfoService {
	
	@Resource
	private ArticleInfoDao articleInfoDao;
	
	public void add(ArticleInfo bean){
		articleInfoDao.add(bean);
	}
	
	public void update(ArticleInfo bean){
		articleInfoDao.update(bean);
	}
	
	public List<ArticleInfo> query(int curPage, int pageSize){
		return articleInfoDao.query(curPage, pageSize);
	}
	
	public ArticleInfo queryById(Long id){
		return articleInfoDao.queryById(id);
	}

	public List<ArticleInfo> queryByPid(Long pId) {
		return articleInfoDao.queryByPid(pId);
	}
	
}
