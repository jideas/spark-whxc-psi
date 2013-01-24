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
	 * �û�ID
	 */
	protected GUID id;

	/**
	 * �û�����
	 */
	protected String name = "";

	/**
	 * �ֻ�����
	 */
	protected String mobileNumber = "";

	/**
	 * ���֤����
	 */
	protected String idNumber = "";

	/**
	 * ����
	 */
	protected long birthday;

	/**
	 * email��ַ
	 */
	protected String email = "";

	/**
	 * ְλ
	 */
	protected String position = "";
	
	/**
	 * �̻�
	 */
	protected String landLineNumber = "";
	
	/**
	 * ͷ��id
	 */
	protected GUID logo;

	/**
	 * ��ɫ
	 */
	protected int roles;

	/**
	 * ��ɫ����
	 */
	protected String rolesInfo;

	/**
	 * ��������ID
	 */
	protected GUID departmentId;

	/**
	 * ������������
	 */
	protected String departmentName;

	/**
	 * �û�״̬
	 */
	protected EmployeeStatus status;
	
	/**
	 * ��ʽ
	 */
	protected String style;
	
	/**
	 * �ɲ��������б�
	 */
	protected List<Action> actions = new ArrayList<Action>();
	
	

	public Action[] getActions(){
    	return actions.toArray(new Action[actions.size()]);
    }
	
	/**
	 * ���ӿɲ�������
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
