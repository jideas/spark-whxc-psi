package com.spark.psi.publish.base.contact.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ContactType;

/**
 * 新增或者更新个人联系信息
 */
public class UpdateContactInfoTask extends Task<UpdateContactInfoTask.Method>{

	/**
	 * Create: 新增联系人
	 * Update: 更新客户或供应商联系人
	 * UpdateBook: 更新通讯录
	 */
	public static enum Method{
		Create,
		Update,
		UpdateBook
	}

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

	/**
	 * 所属公司（供应商/客户）ID
	 */
	private GUID partnerId;

	/**
	 * 是否主联系人
	 */
	private boolean main;

	/**
	 * 联系人类型
	 */
	private ContactType type;

	/**
	 * @return the id
	 */
	public GUID getId(){
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id){
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName(){
		return nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName){
		this.nickName = nickName;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName(){
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	/**
	 * @return the department
	 */
	public String getDepartment(){
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department){
		this.department = department;
	}

	/**
	 * @return the position
	 */
	public String getPosition(){
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position){
		this.position = position;
	}

	/**
	 * @return the landLineNumber
	 */
	public String getLandLineNumber(){
		return landLineNumber;
	}

	/**
	 * @param landLineNumber
	 *            the landLineNumber to set
	 */
	public void setLandLineNumber(String landLineNumber){
		this.landLineNumber = landLineNumber;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNo(){
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNo(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * @return the sexCode
	 */
	public String getSexCode(){
		return sexCode;
	}

	/**
	 * @param sexCode
	 *            the sexCode to set
	 */
	public void setSexCode(String sexCode){
		this.sexCode = sexCode;
	}

	/**
	 * @return the birth
	 */
	public long getBirth(){
		return birth;
	}

	/**
	 * @param birth
	 *            the birth to set
	 */
	public void setBirth(long birth){
		this.birth = birth;
	}

	/**
	 * @return the msnNumber
	 */
	public String getMsnNumber(){
		return msnNumber;
	}

	/**
	 * @param msnNumber
	 *            the msnNumber to set
	 */
	public void setMsnNumber(String msnNumber){
		this.msnNumber = msnNumber;
	}

	/**
	 * @return the qqNumber
	 */
	public String getQqNumber(){
		return qqNumber;
	}

	/**
	 * @param qqNumber
	 *            the qqNumber to set
	 */
	public void setQqNumber(String qqNumber){
		this.qqNumber = qqNumber;
	}

	/**
	 * @return the wwNumber
	 */
	public String getWwNumber(){
		return wwNumber;
	}

	/**
	 * @param wwNumber
	 *            the wwNumber to set
	 */
	public void setWwNumber(String wwNumber){
		this.wwNumber = wwNumber;
	}

	/**
	 * @return the hobbies
	 */
	public String getHobbies(){
		return hobbies;
	}

	/**
	 * @param hobbies
	 *            the hobbies to set
	 */
	public void setHobbies(String hobbies){
		this.hobbies = hobbies;
	}

	/**
	 * @return the memo
	 */
	public String getRemark(){
		return memo;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo){
		this.memo = memo;
	}

	/**
	 * @return the province
	 */
	public String getProvince(){
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province){
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity(){
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city){
		this.city = city;
	}

	/**
	 * @return the district
	 */
	public String getDistrict(){
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district){
		this.district = district;
	}

	/**
	 * @return the address
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address){
		this.address = address;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode(){
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode){
		this.postCode = postCode;
	}

	/**
	 * @return the partnerId
	 */
	public GUID getPartnerId(){
		return partnerId;
	}

	/**
	 * @param partnerId
	 *            the partnerId to set
	 */
	public void setPartnerId(GUID partnerId){
		this.partnerId = partnerId;
	}

	/**
	 * @return the main
	 */
	public boolean isMain(){
		return main;
	}

	/**
	 * @param main
	 *            the main to set
	 */
	public void setMain(boolean main){
		this.main = main;
	}

	/**
	 * @return the type
	 */
	public ContactType getType(){
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ContactType type){
		this.type = type;
	}

}
