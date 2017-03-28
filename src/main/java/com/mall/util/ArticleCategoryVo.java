package com.mall.util;

import java.util.List;

import com.mall.common.entity.ArticleInfo;

public class ArticleCategoryVo {

	private Long id;
	private String name;
	private List<ArticleInfo> infos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ArticleInfo> getInfos() {
		return infos;
	}
	public void setInfos(List<ArticleInfo> infos) {
		this.infos = infos;
	}
}
