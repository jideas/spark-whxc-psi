package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

public class EmployeeOrmEntity{
	/**
	 * 用户ID
	 */
	protected GUID id;

	/**
	 * 用户姓名
	 */
	protected String name = "";

	/**
	 * 手机号码
	 */
	protected String mobileNumber = "";

	/**
	 * 身份证号码
	 */
	protected String idNumber = "";

	/**
	 * 生日
	 */
	protected long birthday;

	/**
	 * email地址
	 */
	protected String email = "";

	/**
	 * 职位
	 */
	protected String position = "";

	/**
	 * 角色
	 */
	protected int roles;
	
	private GUID tenantId;

	private GUID departmentId;
	
	private long createDate;
	
	private String createPerson;
	
	private GUID logo;
	
	private String landLineNumber;
	
	private String empState;
	
	private String style;
	
	
	private String pyName;
	
	private String pyDuty;
	
	
	
	public String getPyName(){
    	return pyName;
    }

	public void setPyName(String pyName){
    	this.pyName = pyName;
    }

	public String getPyDuty(){
    	return pyDuty;
    }

	public void setPyDuty(String pyDuty){
    	this.pyDuty = pyDuty;
    }

	public String getStyle(){
    	return style;
    }

	public void setStyle(String style){
    	this.style = style;
    }

	public GUID getLogo(){
    	return logo;
    }

	public void setLogo(GUID logo){
    	this.logo = logo;
    }

	public String getLandLineNumber(){
    	return landLineNumber;
    }

	public void setLandLineNumber(String landLineNumber){
    	this.landLineNumber = landLineNumber;
    }

	public GUID getTenantId(){
    	return tenantId;
    }

	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNumber;
	}

	public void setMobileNo(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getRoles() {
		return roles;
	}

	public void setRoles(int roles) {
		this.roles = roles;
	}

	public GUID getDepartmentId() {
		return departmentId;
	}


	public long getCreateDate(){
    	return createDate;
    }

	public void setCreateDate(long createDate){
    	this.createDate = createDate;
    }

	public String getCreatePerson(){
    	return createPerson;
    }

	public void setCreatePerson(String createPerson){
    	this.createPerson = createPerson;
	} 
//
//	public void setCustomers(List<PartnerImpl> customers){
//    	this.customers = customers;
//    }

	public String getEmpState() {
		return empState;
	}

	public void setEmpState(String empState) {
		this.empState = empState;
	}

	public void setDepartmentId(GUID departmentId){
    	this.departmentId = departmentId;
    }

	public Auth[] getAcls(){
	    // TODO Auto-generated method stub
	    return null;
    }

	public boolean hasAuth(Auth... auths){
	    // TODO Auto-generated method stub
	    return false;
    }

}
