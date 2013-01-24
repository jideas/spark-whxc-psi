package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 收货地址
 * @author mengyongfeng
 *
 */
public class DeliveryAddressInfo {
	//id
	private GUID id;
	//省
	private String province;
	//市
	private String city;
	//县
	private String district;
	//详细地址
	private String address;
	//邮政编码
	private String zipcode;
	//名称
	private String name;
	//总经理手机
	private String mobile;
	//总经理电话
	private String tel;
	//创建时间
	private long recordDate;
	
	public String getProvince() {
		return province;
	}
	public String getCity() {
		return city;
	}
	public String getDistrict() {
		return district;
	}
	public String getAddress() {
		return address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public String getName() {
		return name;
	}
	public String getMobile() {
		return mobile;
	}
	public String getTel() {
		return tel;
	}
	
	public long getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(long recordDate) {
		this.recordDate = recordDate;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	
	
	
}
