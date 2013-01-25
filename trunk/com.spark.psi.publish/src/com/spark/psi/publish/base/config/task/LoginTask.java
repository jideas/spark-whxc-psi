package com.spark.psi.publish.base.config.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ��¼
 */
public class LoginTask extends SimpleTask {

	/**
	 * Ա��ID
	 */
	private GUID employeeId;

	/**
	 * �⻧ID
	 */
	private GUID tenantId;

	/**
	 * ��¼ƾ��
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
