package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Partner;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

@StructClass
public class PartnerImpl implements Partner {

	public PartnerImpl(String supplierType) {
		this.supplierType = supplierType;
	}

	private GUID id;
	private String code;
	private String name;// 客户供应商全称

	private String shortName;// 客户供应商简称
	private String workTel;
	private String fax;// 传真
	private PartnerStatus status;
	private EmployeeInfo businessPerson; // 业务负责人
	private String supplierCooperation;
	private double creditAmount; // 信用额度

	private int accountPeriod;// 账期

	private int accountRemind;// 预警天数
	private String linkmanName;
	private String linkmanSuffix;
	private String linkmanTel;
	private String linkmanMobile;
	private String linkmanEmail;

	private PartnerType partnerType; // 类型 客户 or 供应商

	protected EnumEntity province;// 省份

	protected EnumEntity city;// 城市

	protected EnumEntity town;// 区（县）

	protected boolean used;

	protected String address;

	protected String supplierType;

	public String getSupplierCooperation() {
		return supplierCooperation;
	}

	public void setSupplierCooperation(String supplierCooperation) {
		this.supplierCooperation = supplierCooperation;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(int accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	public String getWorkTel() {
		return workTel;
	}

	public void setWorkTel(String workTel) {
		this.workTel = workTel;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public int getAccountRemind() {
		return accountRemind;
	}

	public void setAccountRemind(int accountRemind) {
		this.accountRemind = accountRemind;
	}

	public EnumEntity getTown() {
		return town;
	}

	public void setTown(EnumEntity town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public PartnerStatus getStatus() {
		return status;
	}

	public void setStatus(PartnerStatus status) {
		this.status = status;
	}

	public PartnerType getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(PartnerType partnerType) {
		this.partnerType = partnerType;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public EnumEntity getProvince() {
		return province;
	}

	public void setProvince(EnumEntity province) {
		this.province = province;
	}

	public EnumEntity getCity() {
		return city;
	}

	public void setCity(EnumEntity city) {
		this.city = city;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getLinkmanSuffix() {
		return linkmanSuffix;
	}

	public void setLinkmanSuffix(String linkmanSuffix) {
		this.linkmanSuffix = linkmanSuffix;
	}

	public String getLinkmanTel() {
		return linkmanTel;
	}

	public void setLinkmanTel(String linkmanTel) {
		this.linkmanTel = linkmanTel;
	}

	public String getLinkmanMobile() {
		return linkmanMobile;
	}

	public void setLinkmanMobile(String linkmanMobile) {
		this.linkmanMobile = linkmanMobile;
	}

	public String getLinkmanEmail() {
		return linkmanEmail;
	}

	public void setLinkmanEmail(String linkmanEmail) {
		this.linkmanEmail = linkmanEmail;
	}

	public EmployeeInfo getBusinessPerson() {
		return businessPerson;
	}

	public void setBusinessPerson(EmployeeInfo businessPerson) {
		this.businessPerson = businessPerson;
	}

}
