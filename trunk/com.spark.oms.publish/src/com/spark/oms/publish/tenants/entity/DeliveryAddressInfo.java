package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * �ջ���ַ
 * @author mengyongfeng
 *
 */
public class DeliveryAddressInfo {
	//id
	private GUID id;
	//ʡ
	private String province;
	//��
	private String city;
	//��
	private String district;
	//��ϸ��ַ
	private String address;
	//��������
	private String zipcode;
	//����
	private String name;
	//�ܾ����ֻ�
	private String mobile;
	//�ܾ���绰
	private String tel;
	//����ʱ��
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
