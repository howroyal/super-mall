package com.mall.common.vo;

import java.util.List;

import com.mall.common.entity.CategoryInfo;

public class CategoryTree {
	private Long id;
	private String name;
	private String description;
	private String imgUrl;
	private List<CategoryTree> children;
	
	public CategoryTree(){
	
	}

	public CategoryTree(CategoryInfo info){
		this.id = info.getId();
		this.name = info.getName();
		this.description = info.getDescription();
		this.imgUrl = info.getImgUrl();
	}
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public List<CategoryTree> getChildren() {
		return children;
	}
	public void setChildren(List<CategoryTree> children) {
		this.children = children;
	}
	
}
