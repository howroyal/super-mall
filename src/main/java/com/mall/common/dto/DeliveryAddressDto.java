package com.mall.common.dto;

import java.util.Date;

/**
 * 收货地址信息Dto
 * @author zhibing.dai 
 * 2015年12月2日
 */
public class DeliveryAddressDto extends BaseDto implements Cloneable{

	/*主键*/
	private Long pkid;
	
	/*用户编码*/
	private String memberId;
	
	/*收货人*/
	private String contactName;
	
	/*所在省份pkid*/
	private Long provId;
	
	/*所在地市pkid*/
	private Long cityId;
	
	/*所在区县pkid*/
	private Long areaId;
	
	/*详细地址*/
	private String addressDetail;
	
	/*固定电话*/
	private String contactNumber;
	
	/*手机号码*/
	private String contactTel;
	
	/*邮编*/
	private String postCode;
	
	/*是否常用：1，常用；0，非常用*/
	private String isDefault;
	
	/*最后修改人ID*/
	private Long lastModifiedBy;
	
	/*最后修改时间*/
	private Date lastModifiedTime;
	
	/*是否有效：1，有效； 0，无效*/
	private String valid;
	
	/*所在省份名称*/
	private String provName;
	
	/*所在地市名称*/
	private String cityName;
	
	/*所在区县名称*/
	private String areaName;
	
	public Long getPkid() {
		return pkid;
	}
	public void setPkid(Long pkid) {
		this.pkid = pkid;
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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
	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
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
