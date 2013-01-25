/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.partner.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-5    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.partner.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-5    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * <p>
 * �������༭�ͻ���Ӧ��Task����
 * </p>
 */

public abstract class UpdatePartnerTask<T> extends
		Task<UpdatePartnerTask.Method> {

	public enum Method {
		CREATE, UPDATE
	}

	private GUID id;

	private String name;// �ͻ���Ӧ��ȫ��
	private String partnerNo;
	private String shortName;// �ͻ���Ӧ�̼��
	private String workTel;
	private String fax;// ����
	private String supplierCooperation;
	private PartnerStatus status;
	private EmployeeInfo businessPerson; // ҵ������
	private String postcode;
	private String remark;
	private double creditAmount; // ���ö��
	private double taxRate;
	private int accountPeriod;// ����

	private int accountRemind;// Ԥ������
	private String linkmanName;
	private String linkmanSuffix;
	private String linkmanTel;
	private String linkmanMobile;
	private String linkmanEmail;
	private String customerType;
	private String taxNumber;
	

	private PartnerType partnerType; // ���� �ͻ� or ��Ӧ��

	protected String province;// ʡ��

	protected String city;// ����

	protected String town;// �����أ�

	protected boolean used;

	protected String address;

	public GUID getId() {
		return id;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getSupplierCooperation() {
		return supplierCooperation;
	}

	public void setSupplierCooperation(String cupplierCooperation) {
		this.supplierCooperation = cupplierCooperation;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getName() {
		return name;
	}

	public String getPartnerNo() {
		return partnerNo;
	}

	public void setPartnerNo(String partnerNo) {
		this.partnerNo = partnerNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getWorkTel() {
		return workTel;
	}

	public void setWorkTel(String workTel) {
		this.workTel = workTel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public PartnerStatus getStatus() {
		return status;
	}

	public void setStatus(PartnerStatus status) {
		this.status = status;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
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
}
