package com.spark.oms.publish.config;

import java.util.ArrayList;

import com.jiuqi.dna.core.type.GUID;

public class TenantsConfig {
	/**
	 * �⻧Id
	 */
	private GUID tenantsId;
	
	/**
	 * �⻧����
	 */
	private String tenantsName;
	
	/**
	 * �⻧����
	 */
	private String tenantsShortName;
	
	/**
	 * ������
	 */
	private String tenantsCode;
	
	/**
	 * �ܾ����ֻ�����
	 */
	private String bossMoblile;
	
	/**
	 * �ܾ�������
	 */
	private String bossName;
	
	ArrayList<ServiceConfig> services = new ArrayList<ServiceConfig>();
	
	public void addService(ServiceConfig service){
		services.add(service);
	}

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

	public String getTenantsCode() {
		return tenantsCode;
	}

	public void setTenantsCode(String tenantsCode) {
		this.tenantsCode = tenantsCode;
	}

	public String getBossMoblile() {
		return bossMoblile;
	}

	public void setBossMoblile(String bossMoblile) {
		this.bossMoblile = bossMoblile;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public ArrayList<ServiceConfig> getServices() {
		return services;
	}

	public void setServices(ArrayList<ServiceConfig> services) {
		this.services = services;
	}

	public String getTenantsShortName() {
		return tenantsShortName;
	}

	public void setTenantsShortName(String tenantsShortName) {
		this.tenantsShortName = tenantsShortName;
	}
	
}
