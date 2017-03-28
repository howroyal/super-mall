/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月28日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
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
@Table(name = "delivery_address", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class DeliveryAddress {

	@Id
	@SequenceGenerator(name = "delivery_address")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "member_id")
	private String memberId;
	
	@Column(name = "prov_id")
	private Long provId;
	
	@Column(name = "city_id")
	private Long cityId;
	
	@Column(name = "area_id")
	private Long areaId;
	
	@Column(name = "address_detail")
	private String addressDetail;
	
	@Column(name = "contact_name")
	private String contactName;
	
	@Column(name = "contact_phone")
	private String contactPhone;
	
	@Column(name = "contact_tel")
	private String contactTel;
	
	@Column(name = "post_code")
	private String postCode;
	
	@Column(name = "is_default")
	private Integer isDefault;
	
	@Column(name = "valid")
	private Integer valid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getProvId() {
		return provId;
	}

	public void setProvId(Long provId) {
		this.provId = provId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
}
