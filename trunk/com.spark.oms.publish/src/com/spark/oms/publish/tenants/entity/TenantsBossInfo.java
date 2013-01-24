package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;

public class TenantsBossInfo {
	
	//标识
	private GUID tenant;	
	//总经理姓名
	private String bossName;
	//总经理性别
	private String bossSex;
	//总经理email
	private String bossEmail;
	//总经理手机
	private String bossMobile;
	//总经理电话
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