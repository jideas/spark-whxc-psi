package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;
/**
 * ��ϵ��ʵ��
 */
public class RelatorInfo {
	//��ʶ
	private GUID id;
	//����
	private String name;
	//�Ա�
	private String sex;
	//�ֻ�
	private String mobile;
	//�绰
	private String tel;
	//email
	private String email;
	//ְ��
	private String duty;
	//�Ƿ��ܾ���
	private String isBoss;
	//�⻧id
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
