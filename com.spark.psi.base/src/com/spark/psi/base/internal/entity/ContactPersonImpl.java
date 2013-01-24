package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>联系人实体类</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-3
 */

public class ContactPersonImpl{

	/**GUID*/
	private GUID id;

	/**租户Guid*/
	private GUID tenantsGuid;

	/**联系人姓名*/
	private String name;

	/**联系人姓名拼音*/
	private String namePY;

	/**是否为主联系人*/
	private Boolean isMain;

	/**性别*/
	private String sex;

	/**尊称*/
	private String nickname;

	/**手机*/
	private String mobile;

	/**固话*/
	private String phone;

	/**邮箱*/
	private String email;

	/**公司*/
	private String company;

	/**公司拼音*/
	private String companyPY;

	/**部门*/
	private String department;

	/**职位*/
	private String job;

	/**职位拼音*/
	private String jobPY;

	/**QQ*/
	private String qq;

	/**MSN*/
	private String msn;

	/**旺旺*/
	private String aliim;

	/**生日*/
	private Long birthday;

	/**爱好*/
	private String favorite;

	/**备注*/
	private String comments;

	/**类型*/
	private String type;

	/**创建人Guid*/
	private GUID createPersonGuid;

	/**创建人*/
	private String createPerson;

	/**创建日期*/
	private Long createDate;

	/**客户供应商Guid*/
	private GUID cusproGuid;

	/**省份*/
	private String province;

	/**城市*/
	private String city;

	/**地址*/
	private String adress;

	/**详细地址*/
	private String detailAdress;

	/**邮编*/
	private String postCode;

	/**最后访问时间*/
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
