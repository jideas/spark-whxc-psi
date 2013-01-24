package com.spark.psi.base.browser.start;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>临时缓存用户信息</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-2
 */

public class TempUserInfo{
	/**ID*/
	private GUID id;

	/**姓名*/
	private String name;

	/**手机*/
	private String mobile;

	/**身份证号*/
	private String identityNumber;

	/**邮箱*/
	private String email;

	/**岗位*/
	private String job;

	/**部门ID*/
	private GUID departmentId;

	/**部门*/
	private String departmentName;

	/**权限*/
	private int roles;

	/**权限信息*/
	private String rolesName;

	/**是否创建*/
	private boolean isCreate;

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

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getIdentityNumber(){
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber){
		this.identityNumber = identityNumber;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getJob(){
		return job;
	}

	public void setJob(String job){
		this.job = job;
	}

	public GUID getDepartmentId(){
		return departmentId;
	}

	public void setDepartmentId(GUID departmentId){
		this.departmentId = departmentId;
	}

	public String getDepartmentName(){
		return departmentName;
	}

	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}

	public int getRoles(){
		return roles;
	}

	public void setRoles(int roles){
		this.roles = roles;
	}

	public String getRolesName(){
		return rolesName;
	}

	public void setRolesName(String rolesName){
		this.rolesName = rolesName;
	}

	public boolean isCreate(){
		return isCreate;
	}

	public void setCreate(boolean isCreate){
		this.isCreate = isCreate;
	}

}
