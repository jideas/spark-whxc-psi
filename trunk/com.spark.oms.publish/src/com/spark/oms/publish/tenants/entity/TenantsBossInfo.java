package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;

public class TenantsBossInfo {
	
	//��ʶ
	private GUID tenant;	
	//�ܾ�������
	private String bossName;
	//�ܾ����Ա�
	private String bossSex;
	//�ܾ���email
	private String bossEmail;
	//�ܾ����ֻ�
	private String bossMobile;
	//�ܾ���绰
	private String bossTel;
	
	public GUID getTenant() {
		return tenant;
	}
	public void setTenant(GUID tenant) {
		this.tenant = tenant;
	}
	public String getBossName() {
		return bossName;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	public String getBossSex() {
		return bossSex;
	}
	public void setBossSex(String bossSex) {
		this.bossSex = bossSex;
	}
	public String getBossEmail() {
		return bossEmail;
	}
	public void setBossEmail(String bossEmail) {
		this.bossEmail = bossEmail;
	}
	public String getBossMobile() {
		return bossMobile;
	}
	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}
	public String getBossTel() {
		return bossTel;
	}
	public void setBossTel(String bossTel) {
		this.bossTel = bossTel;
	}
}