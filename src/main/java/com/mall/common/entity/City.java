/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月28日
 * @Copyright:Copyright (c) 2015 - 2100
 */
package com.mall.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月28日
 *@Version:1.1.0
 */
@Entity
@Table(name = "city", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class City {

	@Id
	@SequenceGenerator(name = "city")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "code")
	private String code;

	@Column(name = "PROVINCE_CODE")
	private String provinceCode;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "valid")
	private Integer valid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
}
