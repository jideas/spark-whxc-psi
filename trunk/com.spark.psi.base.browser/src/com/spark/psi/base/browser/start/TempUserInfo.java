package com.spark.psi.base.browser.start;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ʱ�����û���Ϣ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-2
 */

public class TempUserInfo{
	/**ID*/
	private GUID id;

	/**����*/
	private String name;

	/**�ֻ�*/
	private String mobile;

	/**���֤��*/
	private String identityNumber;

	/**����*/
	private String email;

	/**��λ*/
	private String job;

	/**����ID*/
	private GUID departmentId;

	/**����*/
	private String departmentName;

	/**Ȩ��*/
	private int roles;

	/**Ȩ����Ϣ*/
	private String rolesName;

	/**�Ƿ񴴽�*/
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
