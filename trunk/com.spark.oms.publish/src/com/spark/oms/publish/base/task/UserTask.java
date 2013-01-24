package com.spark.oms.publish.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.EnabledStatus;
import com.spark.oms.publish.UserStatus;
import com.spark.oms.publish.UserType;

/**
 * 新增、编辑用户
 */
public class UserTask extends SimpleTask {
	
	private GUID RECID;
	// 用户编号
	private String userCode;
	// 用户名
	private String userName;
	// 手机
	private String mobilePhone;
	// 岗位
	private String duty;
	// 部门
	private String department;
	// 电子邮件
	private String email;
	// 工作地点
	private String workingProvince;
	private String workingCity;
	private String workingCounty;
	// 入职地点
	private String province;
	private String city;
	private String county;
	// 入职日期
	private long entryDate;
	// 销售职级
	private String salesRank;
	// 类型
	private UserType userType;
	// 状态
	private UserStatus userstatus;
	// 离职日期
	private long terminalDate;
	// 创建人
	private GUID createPerson;
	// 创建人姓名
	private String createPersonName;
	// 创建日期
	private long createDate;
	// 修改人
	private GUID updatePerson;
	// 修改人姓名
	private String updatePersonName;
	// 修改日期
	private long updateDate;
	// 登录密码
	private String passWord;
	//用户启用状态
	private EnabledStatus enabledstatus;
	//用户角色
	private UserRole[] roles;
	
	//添加2个区域
	// 工作地点
	private String workingArea;
	// 入职地点
	private String area;
		
	public String getWorkingArea() {
		return workingArea;
	}

	public void setWorkingArea(String workingArea) {
		this.workingArea = workingArea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public EnabledStatus getEnabledStatus() {
		return enabledstatus;
	}

	public void setEnabledStatus(EnabledStatus enabledstatus) {
		this.enabledstatus = enabledstatus;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public GUID getRECID() {
		return RECID;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkingProvince() {
		return workingProvince;
	}

	public void setWorkingProvince(String workingProvince) {
		this.workingProvince = workingProvince;
	}

	public String getWorkingCity() {
		return workingCity;
	}

	public void setWorkingCity(String workingCity) {
		this.workingCity = workingCity;
	}

	public String getWorkingCounty() {
		return workingCounty;
	}

	public void setWorkingCounty(String workingCounty) {
		this.workingCounty = workingCounty;
	}

	public UserStatus getUserStatus() {
		return userstatus;
	}

	public void setUserStatus(UserStatus userstatus) {
		this.userstatus = userstatus;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public long getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(long entryDate) {
		this.entryDate = entryDate;
	}

	public String getSalesRank() {
		return salesRank;
	}

	public void setSalesRank(String salesRank) {
		this.salesRank = salesRank;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	public long getTerminalDate() {
		return terminalDate;
	}

	public void setTerminalDate(long terminalDate) {
		this.terminalDate = terminalDate;
	}

	public GUID getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(GUID createPerson) {
		this.createPerson = createPerson;
	}

	public String getCreatePersonName() {
		return createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public GUID getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(GUID updatePerson) {
		this.updatePerson = updatePerson;
	}

	public String getUpdatePersonName() {
		return updatePersonName;
	}

	public void setUpdatePersonName(String updatePersonName) {
		this.updatePersonName = updatePersonName;
	}

	public long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(long updateDate) {
		this.updateDate = updateDate;
	}
	
	
	public UserRole[] getRoles() {
		return roles;
	}

	public void setRoles(UserRole[] roles) {
		this.roles = roles;
	}

	public static final class UserRole{
		
		private GUID RECID;
		public GUID getRECID() {
			return RECID;
		}
		public void setRECID(GUID rECID) {
			RECID = rECID;
		}
		private String roleCode;
		private String roleId;
		private String roleName;
		
		public String getRoleCode() {
			return roleCode;
		}
		public void setRoleCode(String roleCode) {
			this.roleCode = roleCode;
		}
		public String getRoleId() {
			return roleId;
		}
		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
	}
	
	//加操作成功标志与错误提示字符串
	private boolean isSuccess = false;
	
	private String eMsg;

	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String geteMsg() {
		return eMsg;
	}
	public void seteMsg(String eMsg) {
		this.eMsg = eMsg;
	}	
}