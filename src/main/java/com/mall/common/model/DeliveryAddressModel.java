package com.mall.common.model;

/**
 * 收货地址信息Model
 * @author zhibing.dai 
 * 2015年12月2日
 */
public class DeliveryAddressModel{

	/*主键*/
	private Long id;
	
	/*用户编码*/
	private String memberId;
	
	/*收货人*/
	private String contactName;
	
	/*所属省份主键*/
	private Long provId;
	
	/*所属地市主键*/
	private Long cityId;
	
	/*所属区县主键*/
	private Long areaId;
	
	/*详细地址*/
	private String addressDetail;
	
	/*固定电话*/
	private String contactTel;
	
	/*手机号码*/
	private String contactPhone;
	
	/*邮编*/
	private String postCode;
	
	/*是否常用：1，常用； 0，不常用*/
	private String isDefault;
	
	/*是否有效：0，有效 1，无效；*/
	private Integer valid;
	
	private String provName;
	private String cityName;
	private String areaName;
	
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
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getProvName() {
		return provName;
	}
	public void setProvName(String provName) {
		this.provName = provName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
