package com.spark.oms.publish.event;

import java.util.Date;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * �����¼���
 */
public class ServiceEvent extends Event{
	
	/**
	 * �⻧Id
	 */
	private GUID tenantsRecid;
	
	/**
	 * �⻧����
	 */
	private String tenantsName;
	
	/**
	 * �⻧���
	 */
	private String tenantsShortName;
	
	/**
	 * �⻧�ܾ����ֻ�����
	 */
	private String bossMobile;
	
	/**
	 * �ܾ�������
	 */
	private String bossName;
	
	/**
	 * �ܾ���绰
	 */
	private String bossTel;
	
	/**
	 * �ܾ����Ա�
	 */
	private String bossSex;
	
	/**
	 * ϵͳ��¼��
	 */
	private String loginName;
	
	/**
	 * ϵͳ����
	 */
	private String systemName;
	
	/**
	 * ��������ַ
	 */
	private String hostAddr;
	
	/**
	 * �����Ʒ���
	 */
	private String productCategory;
	
	/**
	 * ��Ʒϵ��
	 */
	private String productSerials;
	
	/**
	 * ��Ʒ����
	 */
	private String productName;
	
	/**
	 * ��Ʒ��ʶ
	 */
	private String productCode;
	
	/**
	 * ����ʼ����
	 */
	private Date serviceBeginDate;
	
	/**
	 * �����������
	 */
	private Date serviceEndDate;
	
	/**
	 * ������������
	 */
	private int userNumControlToplimit;
	
	/**
	 * ������Ϣ
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
