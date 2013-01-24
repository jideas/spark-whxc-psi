package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ContactType;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;

public class ContactBookInfoImpl implements ContactBookInfo{

	protected GUID id;

	/**
	 * 联系人名称
	 */
	protected String name;

	/**
	 * 昵称
	 */
	protected String nickName;

	/**
	 * 所属客户或者供应商的ID
	 */
	protected GUID partnerId;

	/**
	 * 所属客户或者供应商的名称
	 */
	protected String partnerName;

	/**
	 * 单位
	 */
	protected String company;

	/**
	 * 部门
	 */
	protected String department;

	/**
	 * 职位
	 */
	protected String position;

	/**
	 * 固定电话
	 */
	protected String landLineNumber;

	/**
	 * 手机
	 */
	protected String mobileNumber;

	/**
	 * email地址
	 */
	protected String email;

	/**
	 * 性别
	 */
	protected EnumEntity sex;

	/**
	 * 生日
	 */
	protected long birth;

	/**
	 * MSN号
	 */
	protected String msnNumber;

	/**
	 * QQ号
	 */
	protected String qqNumber;

	/**
	 * 旺旺号
	 */
	protected String wwNumber;

	/**
	 * 爱好
	 */
	protected String hobbies;

	/**
	 * 备注
	 */
	protected String memo;

	/**
	 * 联系人类型
	 */
	protected ContactType type;

	/**
	 * 地址所在省份
	 */
	protected EnumEntity province;// 省份

	/**
	 * 地址所在城市
	 */
	protected EnumEntity city;// 城市

	/**
	 * 地址所在区县
	 */
	protected EnumEntity district;

	/**
	 * 地址
	 */
	protected String address;

	/**
	 * 邮政编码
	 */
	protected String postCode;

	/**
	 * 主联系人
	 */
	protected boolean main;
	
	protected long lastDate;
	
	

	public long getLastDate(){
    	return lastDate;
    }

	public void setLastDate(long lastDate){
    	this.lastDate = lastDate;
    }

	/**
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName(){
		return nickName;
	}

	/**
	 * @return the partnerId
	 */
	public GUID getPartnerId(){
		return partnerId;
	}

	/**
	 * @return the partnerName
	 */
	public String getPartnerName(){
		return partnerName;
	}

	public String getCompany(){
		return company;
	}

	public void setCompany(String company){
		this.company = company;
	}

	/**
	 * @return the department
	 */
	public String getDepartment(){
		return department;
	}

	/**
	 * @return the position
	 */
	public String getPosition(){
		return position;
	}

	/**
	 * @return the landLineNumber
	 */
	public String getLandLineNumber(){
		return landLineNumber;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNo(){
		return mobileNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * @return the sex
	 */
	public EnumEntity getSex(){
		return sex;
	}

	/**
	 * @return the birth
	 */
	public long getBirth(){
		return birth;
	}

	/**
	 * @return the msnNumber
	 */
	public String getMsnNumber(){
		return msnNumber;
	}

	/**
	 * @return the qqNumber
	 */
	public String getQqNumber(){
		return qqNumber;
	}

	/**
	 * @return the wwNumber
	 */
	public String getWwNumber(){
		return wwNumber;
	}

	/**
	 * @return the hobbies
	 */
	public String getHobbies(){
		return hobbies;
	}

	/**
	 * @return the memo
	 */
	public String getRemark(){
		return memo;
	}

	/**
	 * @return the type
	 */
	public ContactType getType(){
		return type;
	}

	/**
	 * @return the province
	 */
	public EnumEntity getProvince(){
		return province;
	}

	/**
	 * @return the city
	 */
	public EnumEntity getCity(){
		return city;
	}

	/**
	 * @return the district
	 */
	public EnumEntity getDistrict(){
		return district;
	}

	/**
	 * @return the address
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode(){
		return postCode;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setNickName(String nickName){
		this.nickName = nickName;
	}

	public void setPartnerId(GUID partnerId){
		this.partnerId = partnerId;
	}

	public void setPartnerName(String partnerName){
		this.partnerName = partnerName;
	}

	public void setDepartment(String department){
		this.department = department;
	}

	public void setPosition(String position){
		this.position = position;
	}

	public void setLandLineNumber(String landLineNumber){
		this.landLineNumber = landLineNumber;
	}

	public void setMobileNo(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setSex(EnumEntity sex){
		this.sex = sex;
	}

	public void setBirth(long birth){
		this.birth = birth;
	}

	public void setMsnNumber(String msnNumber){
		this.msnNumber = msnNumber;
	}

	public void setQqNumber(String qqNumber){
		this.qqNumber = qqNumber;
	}

	public void setWwNumber(String wwNumber){
		this.wwNumber = wwNumber;
	}

	public void setHobbies(String hobbies){
		this.hobbies = hobbies;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}

	public void setType(ContactType type){
		this.type = type;
	}

	public void setProvince(EnumEntity province){
		this.province = province;
	}

	public void setCity(EnumEntity city){
		this.city = city;
	}

	public void setDistrict(EnumEntity district){
		this.district = district;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public void setPostCode(String postCode){
		this.postCode = postCode;
	}

	public GUID getId(){
		return id;
	}

	public void setId(GUID id){
		this.id = id;
	}

	/**
	 * @return the main
	 */
	public boolean isMain(){
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(boolean main){
		this.main = main;
	}

}
