package com.mall.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**   
 * 商品类目支持多级菜单
 * @ClassName  CategoryInfo   
 * @Description TODO   
 * @author 王浩  
 * @date   2015-8-6 下午12:17:17   
 *      
 */  
@Entity
@Table(name = "category_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class CategoryInfo {
	//id
	@Id
	@SequenceGenerator(name = "category_info")
	@Column(name = "id")
	private Long id;
	/**   
	 * 名称  
	 */ 
	@Column(name = "name")
	private String name;
	
	/**   
	 * 节点的图像地址   
	 */ 
	@Column(name = "img_url")
	private String imgUrl;
	
	/**
	 * 描述,可用于促销用语
	 */
	@Column(name = "description")
	private String description;
	
	/**   
	 * 父节点名称 目前只支持单一父节点   
	 */ 
	@Column(name = "parent_id")
	private Long parentId;
	
	/**
	 * 是否品牌
	 */
	@Column(name = "is_band")
	private int isBand;

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public int getIsBand() {
		return isBand;
	}

	public void setIsBand(int isBand) {
		this.isBand = isBand;
	}
}
