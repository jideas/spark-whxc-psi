package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * 
 */
public interface Login {

	/**
	 * @return the employeeId
	 */
	public GUID getEmployeeId();

	/**
	 * @return the tenantId
	 */
	public GUID getTenantId();

	/**
	 * 
	 * @param auths
	 * @return
	 */
	public boolean hasAuth(Auth... auths);

	/**
	 * 
	 * @return
	 */
	public Auth[] getAcls();
}
