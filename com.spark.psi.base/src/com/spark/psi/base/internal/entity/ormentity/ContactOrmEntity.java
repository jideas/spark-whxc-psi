package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;

public class ContactOrmEntity{
	
	/**
	 * ��ĿID
	 */
	private GUID id;

	/**
	 * ��ϵ������
	 */
	private String name;

	/**
	 * �ǳ�
	 */
	private String nickName;

	/**
	 * ��λ����
	 */
	private String companyName;

	/**
	 * ����
	 */
	private String department;

	/**
	 * ְλ
	 */
	private String position;
	
	private String postionPy;
	
	
	private String type;

	/**
	 * �̶��绰
	 */
	private String landLineNumber;

	/**
	 * �ֻ�
	 */
	private String mobileNumber;

	/**
	 * email��ַ
	 */
	private String email;

	/**
	 * �Ա�
	 */
	private String sexCode;

	/**
	 * ����
	 */
	private long birth;

	/**
	 * MSN��
	 */
	private String msnNumber;

	/**
	 * QQ��
	 */
	private String qqNumber;

	/**
	 * ������
	 */
	private String wwNumber;

	/**
	 * ����
	 */
	private String hobbies;

	/**
	 * ��ע
	 */
	private String memo;

	/**
	 * ʡ��
	 */
	private String province;

	/**
	 * ����
	 */
	private String city;

	/**
	 * ����
	 */
	private String district;

	/**
	 * ��ϸ��ַ
	 */
	private String address;

	/**
	 * �ʱ�
	 */
	private String postCode;
	
	private GUID tenantId;
	
	private String namePy;
	
	/**
	 * �Ƿ�����ϵ��
	 */
	private boolean main;
	
	private String companyPy;
	
	private GUID createPersonId;
	
	private String creaetPerson;
	
	private long createDate;
	
	/**
	 * �ͻ���Ӧ��Id
	 */
	private GUID partnerId;
	
	private long lastDate;
	
	

	public long getLastDate(){
    	return lastDate;
    }

	public void setLastDate(long lastDate){
    	this.lastDate = lastDate;
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

	public String getNickName(){
    	return nickName;
    }

	public void setNickName(String nickName){
    	this.nickName = nickName;
    }

	public String getCompanyName(){
    	return companyName;
    }

	public void setCompanyName(String companyName){
    	this.companyName = companyName;
    }

	public String getDepartment(){
    	return department;
    }

	public void setDepartment(String department){
    	this.department = department;
    }

	public String getPosition(){
    	return position;
    }

	public void setPosition(String position){
    	this.position = position;
    }

	public String getPostionPy(){
    	return postionPy;
    }

	public void setPostionPy(String postionPy){
    	this.postionPy = postionPy;
    }

	public String getType(){
    	return type;
    }

	public void setType(String type){
    	this.type = type;
    }

	public String getLandLineNumber(){
    	return landLineNumber;
    }

	public void setLandLineNumber(String landLineNumber){
    	this.landLineNumber = landLineNumber;
    }

	public String getMobileNo(){
    	return mobileNumber;
    }

	public void setMobileNo(String mobileNumber){
    	this.mobileNumber = mobileNumber;
    }

	public String getEmail(){
    	return email;
    }

	public void setEmail(String email){
    	this.email = email;
    }

	public String getSexCode(){
    	return sexCode;
    }

	public void setSexCode(String sexCode){
    	this.sexCode = sexCode;
    }

	public long getBirth(){
    	return birth;
    }

	public void setBirth(long birth){
    	this.birth = birth;
    }

	public String getMsnNumber(){
    	return msnNumber;
    }

	public void setMsnNumber(String msnNumber){
    	this.msnNumber = msnNumber;
    }

	public String getQqNumber(){
    	return qqNumber;
    }

	public void setQqNumber(String qqNumber){
    	this.qqNumber = qqNumber;
    }

	public String getWwNumber(){
    	return wwNumber;
    }

	public void setWwNumber(String wwNumber){
    	this.wwNumber = wwNumber;
    }

	public String getHobbies(){
    	return hobbies;
    }

	public void setHobbies(String hobbies){
    	this.hobbies = hobbies;
    }

	public String getRemark(){
    	return memo;
    }

	public void setMemo(String memo){
    	this.memo = memo;
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

	public String getDistrict(){
    	return district;
    }

	public void setDistrict(String district){
    	this.district = district;
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

	public GUID getTenantId(){
    	return tenantId;
    }

	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }

	public String getNamePy(){
    	return namePy;
    }

	public void setNamePy(String namePy){
    	this.namePy = namePy;
    }

	public boolean isMain(){
    	return main;
    }

	public void setMain(boolean main){
    	this.main = main;
    }

	public String getCompanyPy(){
    	return companyPy;
    }

	public void setCompanyPy(String companyPy){
    	this.companyPy = companyPy;
    }

	public GUID getCreatePersonId(){
    	return createPersonId;
    }

	public void setCreatePersonId(GUID createPersonId){
    	this.createPersonId = createPersonId;
    }

	public String getCreaetPerson(){
    	return creaetPerson;
    }

	public void setCreaetPerson(String creaetPerson){
    	this.creaetPerson = creaetPerson;
    }

	public long getCreateDate(){
    	return createDate;
    }

	public void setCreateDate(long createDate){
    	this.createDate = createDate;
    }

	public GUID getPartnerId(){
    	return partnerId;
    }

	public void setPartnerId(GUID partnerId){
    	this.partnerId = partnerId;
    }

	
	
}
