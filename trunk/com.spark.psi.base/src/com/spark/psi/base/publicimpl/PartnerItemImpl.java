package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.partner.entity.CustomerItem;
import com.spark.psi.publish.base.partner.entity.SupplierItem;

public class PartnerItemImpl implements CustomerItem,SupplierItem{

	protected String name;// �ͻ���Ӧ��ȫ��
	
	protected String supplierType;
	protected double taxRate;
	protected String partnerNo;

	protected String shortName;// �ͻ���Ӧ�̼��

	protected Double tradeTotalAmount; // �ܽ��

	protected int tradeTotalCount; // ����

	protected long recentTradeDate; // �����������

	protected Double balanceAmount; // Ӧ��Ӧ�� ���

	protected GUID contactId;   //��ϵ��ID

	protected String contactName; //��ϵ������
	
	protected String contactMobileNo; //��ϵ���ֻ�
	
	protected String contactLandLineNumber; //��ϵ�˹̻�
	
	protected String contactEmail; //��ϵ�˵����ʼ�
	
	protected Double creditAmount; // ���ö��

	protected int creditDay; // ��������

	protected boolean creditExpired; // �Ƿ��ѹ�����
	
	protected long remindDay;// Ԥ������

	protected GUID salesmanId;   //������ԱID
 
	protected String salesmanName; //������Ա����
	
	protected String province;  //ʡ��
	
	protected String city; //����
	
	protected String address; //��ϸ��ַ
	
	protected GUID id;
	
	protected boolean creditTowards; //�ٽ�����
	
	protected boolean used; //�Ƿ���ʹ��
	
	
	

	public void setUsed(boolean used){
    	this.used = used;
    }

	public boolean isUsed(){
    	return used;
    }

	public boolean isCreditTowards(){
    	return creditTowards;
    }

	public void setCreditTowards(boolean creditTowards){
    	this.creditTowards = creditTowards;
    }

	public String getProvince(){
    	return province;
    }

	public String getPartnerNo() {
		return partnerNo;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public void setPartnerNo(String partnerNo) {
		this.partnerNo = partnerNo;
	}

	public void setProvince(String province){
    	this.province = province;
    }

	public String getCity(){
    	return city;
    }

	public void setCity(String city){
    	this.city = city;
    }

	public String getAddress(){
    	return address;
    }

	public void setAddress(String address){
    	this.address = address;
    }

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public Double getCreditAmount(){
    	return creditAmount;
    }

	public int getAccountPeriod(){
    	return creditDay;
    }

	public boolean isCreditExpired(){
    	return creditExpired;
    }

	public long getRemindDay(){
    	return remindDay;
    }

	public GUID getSalesmanId(){
    	return salesmanId;
    }

	public String getSalesmanName(){
    	return salesmanName;
    }

	public String getName(){
    	return name;
    }

	public String getShortName(){
    	return shortName;
    }

	public Double getTradeTotalAmount(){
    	return tradeTotalAmount;
    }

	public int getTradeTotalCount(){
    	return tradeTotalCount;
    }

	public long getRecentTradeDate(){
    	return recentTradeDate;
    }

	public Double getBalanceAmount(){
    	return balanceAmount;
    }

	public GUID getContactId(){
    	return contactId;
    }

	public String getContactName(){
    	return contactName;
    }

	public void setName(String name){
    	this.name = name;
    }

	public void setShortName(String shortName){
    	this.shortName = shortName;
    }

	public void setTradeTotalAmount(Double tradeTotalAmount){
    	this.tradeTotalAmount = tradeTotalAmount;
    }

	public void setTradeTotalCount(int tradeTotalCount){
    	this.tradeTotalCount = tradeTotalCount;
    }

	public void setRecentTradeDate(long recentTradeDate){
    	this.recentTradeDate = recentTradeDate;
    }

	public void setBalanceAmount(Double balanceAmount){
    	this.balanceAmount = balanceAmount;
    }

	public void setContactId(GUID contactId){
    	this.contactId = contactId;
    }

	public void setContactName(String contactName){
    	this.contactName = contactName;
    }

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public void setCreditAmount(Double creditAmount){
    	this.creditAmount = creditAmount;
    }

	public void setCreditDay(int creditDay){
    	this.creditDay = creditDay;
    }

	public void setCreditExpired(boolean creditExpired){
    	this.creditExpired = creditExpired;
    }

	public void setRemindDay(long remindDay){
    	this.remindDay = remindDay;
    }

	public void setSalesmanId(GUID salesmanId){
    	this.salesmanId = salesmanId;
    }

	public void setSalesmanName(String salesmanName){
    	this.salesmanName = salesmanName;
    }

	public String getContactMobileNo() {
		return contactMobileNo;
	}

	public void setContactMobileNo(String contactMobileNo) {
		this.contactMobileNo = contactMobileNo;
	}

	public String getContactLandLineNumber() {
		return contactLandLineNumber;
	}
	
	public void setContactLandLineNumber(String contactLandLineNumber) {
		this.contactLandLineNumber = contactLandLineNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

}
