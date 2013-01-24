package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class PartnerOrmEntity {
	
	private GUID id;

	private String name;// �ͻ���Ӧ��ȫ��

	private String shortName;// �ͻ���Ӧ�̼��

	protected String province;// ʡ��

	protected String city;// ����
	
	protected String district; //����

	protected String address;// ��ϸ��ַ

	protected String postCode;// �ʱ�

	private String workPhoneNumber;// �����绰

	private String faxNumber;// ����

	private String bankAccountNumber;// �����˺�

	private String bankName;// ����

	private String bankAccountName;// ��������

	private double creditAmount;// ���ö��

	private int creditDay;// ����

	private int warnningDay;// Ԥ������


	private String website;// ��ַ

	private String taxationNumber; // ��ֵ˰��

	private String developType;// �ͻ���Ӧ����Դ

	private String industyType;// ������ҵ����

	private String scaleType;// ��ҵ��ģ����

	private String memo;// ��ע
	
	private GUID tenantId; //�⻧id
	
	private String partnerType; //�ͻ�����
	
	private String createPerson;
	
	private long createDate;
	 /**
	  * �ͻ���Ӧ��״̬ Ǳ�� or ��ʽ
	  */
	private String partnerGrd;
	
	private boolean used;
	
	private GUID creditLinePerson;
	
	
	
	
	public GUID getCreditLinePerson(){
    	return creditLinePerson;
    }

	public void setCreditLinePerson(GUID creditLinePerson){
    	this.creditLinePerson = creditLinePerson;
    }

	public boolean isUsed(){
    	return used;
    }

	public void setUsed(boolean used){
    	this.used = used;
    }

	/**
	 * ҵ������
	 */
	private GUID busPerson;
	
	

	public GUID getBusinessPerson(){
    	return busPerson;
    }

	public void setBusPerson(GUID busPerson){
    	this.busPerson = busPerson;
    }

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public String getName(){
    	return name;
    }

	public void setName(String name){
    	this.name = name;
    }

	public String getShortName(){
    	return shortName;
    }

	public void setShortName(String shortName){
    	this.shortName = shortName;
    }

	public String getProvince(){
    	return province;
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

	public String getPostCode(){
    	return postCode;
    }

	public void setPostCode(String postCode){
    	this.postCode = postCode;
    }

	public String getWorkPhoneNumber(){
    	return workPhoneNumber;
    }

	public void setWorkPhoneNumber(String workPhoneNumber){
    	this.workPhoneNumber = workPhoneNumber;
    }

	public String getFaxNumber(){
    	return faxNumber;
    }

	public void setFaxNumber(String faxNumber){
    	this.faxNumber = faxNumber;
    }

	public String getBankAccountNumber(){
    	return bankAccountNumber;
    }

	public void setBankAccountNumber(String bankAccountNumber){
    	this.bankAccountNumber = bankAccountNumber;
    }

	public String getBankName(){
    	return bankName;
    }

	public void setBankName(String bankName){
    	this.bankName = bankName;
    }

	public String getBankAccountName(){
    	return bankAccountName;
    }

	public void setBankAccountName(String bankAccountName){
    	this.bankAccountName = bankAccountName;
    }

	public double getCreditAmount(){
    	return creditAmount;
    }

	public void setCreditAmount(double creditAmount){
    	this.creditAmount = creditAmount;
    }

	public int getAccountPeriod(){
    	return creditDay;
    }

	public void setCreditDay(int creditDay){
    	this.creditDay = creditDay;
    }

	public int getAccountRemind(){
    	return warnningDay;
    }

	public void setWarnningDay(int warnningDay){
    	this.warnningDay = warnningDay;
    }

	public String getWebsite(){
    	return website;
    }

	public void setWebsite(String website){
    	this.website = website;
    }

	public String getTaxationNumber(){
    	return taxationNumber;
    }

	public void setTaxationNumber(String taxationNumber){
    	this.taxationNumber = taxationNumber;
    }

	public String getDevelopType(){
    	return developType;
    }

	public void setDevelopType(String developType){
    	this.developType = developType;
    }

	public String getIndustyType(){
    	return industyType;
    }

	public void setIndustyType(String industyType){
    	this.industyType = industyType;
    }

	public String getScaleType(){
    	return scaleType;
    }

	public void setScaleType(String scaleType){
    	this.scaleType = scaleType;
    }

	public String getRemark(){
    	return memo;
    }

	public void setMemo(String memo){
    	this.memo = memo;
    }

	public GUID getTenantId(){
    	return tenantId;
    }

	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }

	public String getPartnerType(){
    	return partnerType;
    }

	public void setPartnerType(String partnerType){
    	this.partnerType = partnerType;
    }

	public String getCreatePerson(){
    	return createPerson;
    }

	public void setCreatePerson(String createPerson){
    	this.createPerson = createPerson;
    }

	public long getCreateDate(){
    	return createDate;
    }

	public void setCreateDate(long createDate){
    	this.createDate = createDate;
    }

	public String getDistrict(){
    	return district;
    }

	public void setDistrict(String district){
    	this.district = district;
    }

	public String getPartnerGrd(){
    	return partnerGrd;
    }

	public void setPartnerGrd(String partnerGrd){
    	this.partnerGrd = partnerGrd;
    }
	

}
