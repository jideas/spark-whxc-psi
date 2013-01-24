package com.spark.psi.base.browser.start;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.EmployeeStatus;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;

/**
 * <p>员工实现类</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-4
 */

public class TempEmployeeItemImpl implements EmployeeItem{

	/**用户ID*/
	private GUID id;

	/**用户姓名*/
	private String name = "";

	/**手机号码*/
	private String mobileNumber = "";

	/**身份证号码*/
	private String idNumber = "";

	/**生日*/
	private long birthday;

	/**email地址*/
	private String email = "";

	/**职位*/
	private String position = "";

	/**固话*/
	private String landLineNumber = "";

	/**头像id*/
	private GUID logo;

	/**角色*/
	private int roles;

	/**角色描述*/
	private String rolesInfo;

	/**所属部门ID*/
	private GUID departmentId;

	/**所属部门名称*/
	private String departmentName;

	/**用户状态*/
	private EmployeeStatus status;

	/**样式*/
	private String style;

	/**可操作动作列表*/
	private List<Action> actions = new ArrayList<Action>();

	/**是否禁用*/
	private boolean disable;

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

	public boolean isDisable(){
		return disable;
	}

	public void setDisable(boolean disable){
		this.disable = disable;
	}

}
