package com.spark.uac.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.task.ActivingUserTask.Method;

/**
 * �û�������Ϣ����Task
 * 
 */
public class ActivingUserTask extends Task<Method> {

	public enum Method {
		Add, // ����һ���û�������Ϣ
		Remove // ɾ��ָ���û�������Ϣ
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
