package com.spark.uac.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.task.ActivingUserTask.Method;

/**
 * 用户激活信息处理Task
 * 
 */
public class ActivingUserTask extends Task<Method> {

	public enum Method {
		Add, // 增加一个用户激活信息
		Remove // 删除指定用户激活信息
	}

	private GUID tenantId;

	private GUID userId;

	private String mobileNumber;

	private String userTitle;

	public ActivingUserTask(GUID tenantId, GUID userId, String mobileNumber,
			String userTitle) {
		super();
		this.tenantId = tenantId;
		this.userId = userId;
		this.mobileNumber = mobileNumber;
		this.userTitle = userTitle;
	}

	/**
	 * @return the tenantId
	 */
	public GUID getTenantId() {
		return tenantId;
	}

	/**
	 * @return the userId
	 */
	public GUID getUserId() {
		return userId;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNo() {
		return mobileNumber;
	}

	/**
	 * @return the userTitle
	 */
	public String getUserTitle() {
		return userTitle;
	}

}
