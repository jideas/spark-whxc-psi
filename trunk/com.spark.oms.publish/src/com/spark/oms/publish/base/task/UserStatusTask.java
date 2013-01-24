package com.spark.oms.publish.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.EnabledStatus;
import com.spark.oms.publish.UserStatus;

public class UserStatusTask extends SimpleTask{

	public UserStatusTask(GUID rECID, String userCode, UserStatus userstatus,
			EnabledStatus enabledstatus) {
		super();
		this.RECID = rECID;
		this.userCode = userCode;
		this.userstatus = userstatus;
		this.enabledstatus = enabledstatus;
	}
	
	// 用户Id
	private GUID RECID;
	// 用户编号
	private String userCode;
	// 在职状态
	private UserStatus userstatus;
	// 启用状态
	private EnabledStatus enabledstatus;

	public GUID getRECID() {
		return RECID;
	}
	public String getUserCode() {
		return userCode;
	}
	public UserStatus getUserStatus() {
		return userstatus;
	}
	public EnabledStatus getEnabledStatus() {
		return enabledstatus;
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