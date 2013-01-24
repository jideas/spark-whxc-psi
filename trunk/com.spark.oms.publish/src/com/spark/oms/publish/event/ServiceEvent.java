package com.spark.oms.publish.event;

import java.util.Date;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 服务事件类
 */
public class ServiceEvent extends Event{
	
	/**
	 * 租户Id
	 */
	private GUID tenantsRecid;
	
	/**
	 * 租户名称
	 */
	private String tenantsName;
	
	/**
	 * 租户简称
	 */
	private String tenantsShortName;
	
	/**
	 * 租户总经理手机号码
	 */
	private String bossMobile;
	
	/**
	 * 总经理姓名
	 */
	private String bossName;
	
	/**
	 * 总经理电话
	 */
	private String bossTel;
	
	/**
	 * 总经理性别
	 */
	private String bossSex;
	
	/**
	 * 系统登录名
	 */
	private String loginName;
	
	/**
	 * 系统名称
	 */
	private String systemName;
	
	/**
	 * 服务器地址
	 */
	private String hostAddr;
	
	/**
	 * 服务产品类别
	 */
	private String productCategory;
	
	/**
	 * 产品系列
	 */
	private String productSerials;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 产品标识
	 */
	private String productCode;
	
	/**
	 * 服务开始日期
	 */
	private Date serviceBeginDate;
	
	/**
	 * 服务结束日期
	 */
	private Date serviceEndDate;
	
	/**
	 * 服务人数控制
	 */
	private int userNumControlToplimit;
	
	/**
	 * 配置信息
	 */

	public GUID getTenantsRecid() {
		return tenantsRecid;
	}

	public void setTenantsRecid(GUID tenantsRecid) {
		this.tenantsRecid = tenantsRecid;
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

	public String getTenantsShortName() {
		return tenantsShortName;
	}

	public void setTenantsShortName(String tenantsShortName) {
		this.tenantsShortName = tenantsShortName;
	}

	public String getBossMobile() {
		return bossMobile;
	}

	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public String getBossTel() {
		return bossTel;
	}

	public void setBossTel(String bossTel) {
		this.bossTel = bossTel;
	}

	public String getBossSex() {
		return bossSex;
	}

	public void setBossSex(String bossSex) {
		this.bossSex = bossSex;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getHostAddr() {
		return hostAddr;
	}

	public void setHostAddr(String hostAddr) {
		this.hostAddr = hostAddr;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductSerials() {
		return productSerials;
	}

	public void setProductSerials(String productSerials) {
		this.productSerials = productSerials;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Date getServiceBeginDate() {
		return serviceBeginDate;
	}

	public void setServiceBeginDate(Date serviceBeginDate) {
		this.serviceBeginDate = serviceBeginDate;
	}

	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public int getUserNumControlToplimit() {
		return userNumControlToplimit;
	}

	public void setUserNumControlToplimit(int userNumControlToplimit) {
		this.userNumControlToplimit = userNumControlToplimit;
	}
	
}
