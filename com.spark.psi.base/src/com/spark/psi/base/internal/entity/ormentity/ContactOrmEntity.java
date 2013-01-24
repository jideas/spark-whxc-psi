package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;

public class ContactOrmEntity{
	
	/**
	 * 条目ID
	 */
	private GUID id;

	/**
	 * 联系人名称
	 */
	private String name;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 单位名称
	 */
	private String companyName;

	/**
	 * 部门
	 */
	private String department;

	/**
	 * 职位
	 */
	private String position;
	
	private String postionPy;
	
	
	private String type;

	/**
	 * 固定电话
	 */
	private String landLineNumber;

	/**
	 * 手机
	 */
	private String mobileNumber;

	/**
	 * email地址
	 */
	private String email;

	/**
	 * 性别
	 */
	private String sexCode;

	/**
	 * 生日
	 */
	private long birth;

	/**
	 * MSN号
	 */
	private String msnNumber;

	/**
	 * QQ号
	 */
	private String qqNumber;

	/**
	 * 旺旺号
	 */
	private String wwNumber;

	/**
	 * 爱好
	 */
	private String hobbies;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 区县
	 */
	private String district;

	/**
	 * 详细地址
	 */
	private String address;

	/**
	 * 邮编
	 */
	private String postCode;
	
	private GUID tenantId;
	
	private String namePy;
	
	/**
	 * 是否主联系人
	 */
	private boolean main;
	
	private String companyPy;
	
	private GUID createPersonId;
	
	private String creaetPerson;
	
	private long createDate;
	
	/**
	 * 客户供应商Id
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
