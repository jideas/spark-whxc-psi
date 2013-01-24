package com.spark.psi.base.publicimpl;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.entity.PartnerImpl;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.partner.entity.BankAccountItem;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;

public class PartnerInfoImpl implements CustomerInfo, SupplierInfo {

	private GUID id;

	private String name;// 客户供应商全称
	private String number;
	private String shortName;// 客户供应商简称
	private String workTel;
	private String fax;// 传真
	private PartnerStatus status;
	private EmployeeInfo businessPerson; // 业务负责人
	private double creditAmount; // 信用额度
	private String supplierType;
	private String pricePolicy;
	private String supplierCooperation;
	private String vatNo;
	private int accountPeriod;// 账期
	private double taxRate;
	private int accountRemind;// 预警天数
	private String linkmanName;
	private String linkmanSuffix;
	private String linkmanTel;
	private String linkmanMobile;
	private String linkmanEmail;
	private String noticeAddress;
	private String noticePostcode, customerType, taxNumber;
	private long createDate;
	private GUID creatorId;
	private String creator;

	private PartnerType partnerType; // 类型 客户 or 供应商

	protected EnumEntity province;// 省份

	protected EnumEntity city;// 城市

	protected EnumEntity town;// 区（县）

	protected boolean used;

	protected String address;
	private String postcode;
	private String remark;

	private List<BankAccountItem> banks;

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getWorkTel() {
		return workTel;
	}

	public String getPricePolicy() {
		return pricePolicy;
	}

	public void setPricePolicy(String pricePolicy) {
		this.pricePolicy = pricePolicy;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public GUID getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setWorkTel(String workTel) {
		this.workTel = workTel;
	}

	public GUID getId() {
		return id;
	}

	public String getSupplierCooperation() {
		return supplierCooperation;
	}

	public void setSupplierCooperation(String supplierCooperation) {
		this.supplierCooperation = supplierCooperation;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public List<BankAccountItem> getBanks() {
		return banks;
	}

	public void setBanks(List<BankAccountItem> banks) {
		this.banks = banks;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public String getVatNo() {
		return vatNo;
	}

	public String getNoticeAddress() {
		return noticeAddress;
	}

	public void setNoticeAddress(String noticeAddress) {
		this.noticeAddress = noticeAddress;
	}

	public String getNoticePostcode() {
		return noticePostcode;
	}

	public void setNoticePostcode(String noticePostcode) {
		this.noticePostcode = noticePostcode;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
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

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getShortName() {
		return shortName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public EmployeeInfo getBusinessPerson() {
		return businessPerson;
	}

	public void setBusinessPerson(EmployeeInfo businessPerson) {
		this.businessPerson = businessPerson;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public int getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(int accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	public int getAccountRemind() {
		return accountRemind;
	}

	public void setAccountRemind(int accountRemind) {
		this.accountRemind = accountRemind;
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

	public PartnerType getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(PartnerType partnerType) {
		this.partnerType = partnerType;
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

	public EnumEntity getTown() {
		return town;
	}

	public void setTown(EnumEntity town) {
		this.town = town;
	}

	public PartnerStatus getStatus() {
		return status;
	}

	public void setStatus(PartnerStatus status) {
		this.status = status;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PartnerImpl copyToPartner() {
		PartnerImpl impl = new PartnerImpl(this.supplierType);
		impl.setId(this.getId());
		impl.setAccountPeriod(this.accountPeriod);
		impl.setAccountRemind(this.getAccountRemind());
		impl.setAddress(this.getAddress());
		impl.setBusinessPerson(this.getBusinessPerson());
		impl.setCity(this.getCity());
		impl.setCreditAmount(this.getCreditAmount());
		impl.setFax(this.getFax());
		impl.setLinkmanEmail(this.getLinkmanEmail());
		impl.setLinkmanMobile(this.getLinkmanMobile());
		impl.setLinkmanName(this.getLinkmanName());
		impl.setLinkmanSuffix(this.linkmanSuffix);
		impl.setLinkmanTel(this.linkmanTel);
		impl.setName(this.name);
		impl.setPartnerType(partnerType);
		impl.setProvince(this.province);
		impl.setShortName(shortName);
		impl.setStatus(status);
		impl.setTown(town);
		impl.setUsed(used);
		impl.setWorkTel(workTel);
		impl.setCode(this.number);
		impl.setSupplierCooperation(this.getSupplierCooperation());
		return impl;
	}
}
