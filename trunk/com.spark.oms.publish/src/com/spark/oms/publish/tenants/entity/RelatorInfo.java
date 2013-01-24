package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;
/**
 * 联系人实体
 */
public class RelatorInfo {
	//标识
	private GUID id;
	//姓名
	private String name;
	//性别
	private String sex;
	//手机
	private String mobile;
	//电话
	private String tel;
	//email
	private String email;
	//职责
	private String duty;
	//是否总经理
	private String isBoss;
	//租户id
	private GUID  tenantsId;
	public GUID getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSex() {
		return sex;
	}
	public String getMobile() {
		return mobile;
	}
	public String getTel() {
		return tel;
	}
	public String getEmail() {
		return email;
	}
	public String getDuty() {
		return duty;
	}
	public String getIsBoss() {
		return isBoss;
	}
	public GUID getTenantsId() {
		return tenantsId;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public void setIsBoss(String isBoss) {
		this.isBoss = isBoss;
	}
	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	} 
	
}
