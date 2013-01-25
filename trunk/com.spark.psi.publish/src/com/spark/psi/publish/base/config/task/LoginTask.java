package com.spark.psi.publish.base.config.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 登录
 */
public class LoginTask extends SimpleTask {

	/**
	 * 员工ID
	 */
	private GUID employeeId;

	/**
	 * 租户ID
	 */
	private GUID tenantId;

	/**
	 * 登录凭据
	 */
	private String credential;

	public LoginTask(GUID tenantId, GUID employeeId, String credential) {
		super();
		this.tenantId = tenantId;
		this.employeeId = employeeId;
		this.credential = credential;
	}

	/**
	 * @return the employeeId
	 */
	public GUID getEmployeeId() {
		return employeeId;
	}

	/**
	 * @return the tenantId
	 */
	public GUID getTenantId() {
		return tenantId;
	}

	/**
	 * @return the credential
	 */
	public String getCredential() {
		return credential;
	}

}
