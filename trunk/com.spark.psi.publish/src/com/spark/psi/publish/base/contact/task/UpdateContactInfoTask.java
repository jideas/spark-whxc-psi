package com.spark.psi.publish.base.contact.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ContactType;

/**
 * �������߸��¸�����ϵ��Ϣ
 */
public class UpdateContactInfoTask extends Task<UpdateContactInfoTask.Method>{

	/**
	 * Create: ������ϵ��
	 * Update: ���¿ͻ���Ӧ����ϵ��
	 * UpdateBook: ����ͨѶ¼
	 */
	public static enum Method{
		Create,
		Update,
		UpdateBook
	}

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

	/**
	 * ������˾����Ӧ��/�ͻ���ID
	 */
	private GUID partnerId;

	/**
	 * �Ƿ�����ϵ��
	 */
	private boolean main;

	/**
	 * ��ϵ������
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
