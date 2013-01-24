package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ϵ��ʵ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-3
 */

public class ContactPersonImpl{

	/**GUID*/
	private GUID id;

	/**�⻧Guid*/
	private GUID tenantsGuid;

	/**��ϵ������*/
	private String name;

	/**��ϵ������ƴ��*/
	private String namePY;

	/**�Ƿ�Ϊ����ϵ��*/
	private Boolean isMain;

	/**�Ա�*/
	private String sex;

	/**���*/
	private String nickname;

	/**�ֻ�*/
	private String mobile;

	/**�̻�*/
	private String phone;

	/**����*/
	private String email;

	/**��˾*/
	private String company;

	/**��˾ƴ��*/
	private String companyPY;

	/**����*/
	private String department;

	/**ְλ*/
	private String job;

	/**ְλƴ��*/
	private String jobPY;

	/**QQ*/
	private String qq;

	/**MSN*/
	private String msn;

	/**����*/
	private String aliim;

	/**����*/
	private Long birthday;

	/**����*/
	private String favorite;

	/**��ע*/
	private String comments;

	/**����*/
	private String type;

	/**������Guid*/
	private GUID createPersonGuid;

	/**������*/
	private String createPerson;

	/**��������*/
	private Long createDate;

	/**�ͻ���Ӧ��Guid*/
	private GUID cusproGuid;

	/**ʡ��*/
	private String province;

	/**����*/
	private String city;

	/**��ַ*/
	private String adress;

	/**��ϸ��ַ*/
	private String detailAdress;

	/**�ʱ�*/
	private String postCode;

	/**������ʱ��*/
	private Long lastDate;

	public GUID getId(){
		return id;
	}

	public void setId(GUID id){
		this.id = id;
	}

	public GUID getTenantsGuid(){
		return tenantsGuid;
	}

	public void setTenantsGuid(GUID tenantsGuid){
		this.tenantsGuid = tenantsGuid;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getNamePY(){
		return namePY;
	}

	public void setNamePY(String namePY){
		this.namePY = namePY;
	}

	public Boolean getIsMain(){
		return isMain;
	}

	public void setIsMain(Boolean isMain){
		this.isMain = isMain;
	}

	public String getSex(){
		return sex;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getNickname(){
		return nickname;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getCompany(){
		return company;
	}

	public void setCompany(String company){
		this.company = company;
	}

	public String getCompanyPY(){
		return companyPY;
	}

	public void setCompanyPY(String companyPY){
		this.companyPY = companyPY;
	}

	public String getDepartment(){
		return department;
	}

	public void setDepartment(String department){
		this.department = department;
	}

	public String getJob(){
		return job;
	}

	public void setJob(String job){
		this.job = job;
	}

	public String getJobPY(){
		return jobPY;
	}

	public void setJobPY(String jobPY){
		this.jobPY = jobPY;
	}

	public String getQq(){
		return qq;
	}

	public void setQq(String qq){
		this.qq = qq;
	}

	public String getMsn(){
		return msn;
	}

	public void setMsn(String msn){
		this.msn = msn;
	}

	public String getAliim(){
		return aliim;
	}

	public void setAliim(String aliim){
		this.aliim = aliim;
	}

	public Long getBirthday(){
		return birthday;
	}

	public void setBirthday(Long birthday){
		this.birthday = birthday;
	}

	public String getFavorite(){
		return favorite;
	}

	public void setFavorite(String favorite){
		this.favorite = favorite;
	}

	public String getComments(){
		return comments;
	}

	public void setComments(String comments){
		this.comments = comments;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public GUID getCreatePersonGuid(){
		return createPersonGuid;
	}

	public void setCreatePersonGuid(GUID createPersonGuid){
		this.createPersonGuid = createPersonGuid;
	}

	public String getCreatePerson(){
		return createPerson;
	}

	public void setCreatePerson(String createPerson){
		this.createPerson = createPerson;
	}

	public Long getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Long createDate){
		this.createDate = createDate;
	}

	public GUID getCusproGuid(){
		return cusproGuid;
	}

	public void setCusproGuid(GUID cusproGuid){
		this.cusproGuid = cusproGuid;
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

	public String getAdress(){
		return adress;
	}

	public void setAdress(String adress){
		this.adress = adress;
	}

	public String getDetailAdress(){
		return detailAdress;
	}

	public void setDetailAdress(String detailAdress){
		this.detailAdress = detailAdress;
	}

	public String getPostCode(){
		return postCode;
	}

	public void setPostCode(String postCode){
		this.postCode = postCode;
	}

	public Long getLastDate(){
		return lastDate;
	}

	public void setLastDate(Long lastDate){
		this.lastDate = lastDate;
	}

}
