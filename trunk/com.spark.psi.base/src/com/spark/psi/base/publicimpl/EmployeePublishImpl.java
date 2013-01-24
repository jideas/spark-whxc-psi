package com.spark.psi.base.publicimpl;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.EmployeeStatus;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;

public class EmployeePublishImpl implements EmployeeItem,EmployeeInfo{

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
	 * 固话
	 */
	protected String landLineNumber = "";
	
	/**
	 * 头像id
	 */
	protected GUID logo;

	/**
	 * 角色
	 */
	protected int roles;

	/**
	 * 角色描述
	 */
	protected String rolesInfo;

	/**
	 * 所属部门ID
	 */
	protected GUID departmentId;

	/**
	 * 所属部门名称
	 */
	protected String departmentName;

	/**
	 * 用户状态
	 */
	protected EmployeeStatus status;
	
	/**
	 * 样式
	 */
	protected String style;
	
	/**
	 * 可操作动作列表
	 */
	protected List<Action> actions = new ArrayList<Action>();
	
	

	public Action[] getActions(){
    	return actions.toArray(new Action[actions.size()]);
    }
	
	/**
	 * 增加可操作动作
	 * 
	 * @param action void
	 */
	public void addActions(Action... actions){
		for(Action action : actions){	        
			this.actions.add(action);
        }
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

	public String getMobileNo(){
    	return mobileNumber;
    }

	public void setMobileNo(String mobileNumber){
    	this.mobileNumber = mobileNumber;
    }

	public String getIdNumber(){
    	return idNumber;
    }

	public void setIdNumber(String idNumber){
    	this.idNumber = idNumber;
    }

	
	
	public String getStyle(){
    	return style;
    }

	public void setStyle(String style){
    	this.style = style;
    }

	public long getBirthday(){
    	return birthday;
    }

	public void setBirthday(long birthday){
    	this.birthday = birthday;
    }

	public String getEmail(){
    	return email;
    }

	public void setEmail(String email){
    	this.email = email;
    }

	public String getPosition(){
    	return position;
    }

	public void setPosition(String position){
    	this.position = position;
    }

	public int getRoles(){
    	return roles;
    }

	public void setRoles(int roles){
    	this.roles = roles;
    }

	public String getRolesInfo(){
    	return rolesInfo;
    }

	public void setRolesInfo(String rolesInfo){
    	this.rolesInfo = rolesInfo;
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

	public EmployeeStatus getStatus(){
    	return status;
    }

	public void setStatus(EmployeeStatus status){
    	this.status = status;
    }

	public String getLandLineNumber(){
    	return landLineNumber;
    }

	public void setLandLineNumber(String landLineNumber){
    	this.landLineNumber = landLineNumber;
    }

	public GUID getLogo(){
    	return logo;
    }

	public void setLogo(GUID logo){
    	this.logo = logo;
    }

	
	
}
