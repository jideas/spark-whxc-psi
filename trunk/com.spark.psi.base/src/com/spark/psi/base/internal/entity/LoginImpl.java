package com.spark.psi.base.internal.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Login;
import com.spark.psi.publish.Auth;

@StructClass
public final class LoginImpl implements Login {

	/**
	 * 当前登录员工ID
	 */
	@StructField
	private GUID employeeId;

	/**
	 * 当前登录用户所属租户
	 */
	@StructField
	private GUID tenantId;

	/**
	 * 
	 */
	@StructField
	private Set<Auth> acls;

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(GUID employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @param tenantId
	 *            the tenantId to set
	 */
	public void setTenantId(GUID tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @param acls
	 *            the acls to set
	 */
	public void setAcls(List<Auth> acls) {
		this.acls = new HashSet<Auth>();
		this.acls.addAll(acls);
	}

	/**
	 * 
	 */
	public GUID getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * 
	 */
	public GUID getTenantId() {
		return this.tenantId;
	}

	/**
	 * 
	 */
	public boolean hasAuth(Auth... auths) {
		for (Auth auth : auths) {
			if (acls == null || !acls.contains(auth)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public Auth[] getAcls() {
		return this.acls.toArray(new Auth[0]);
	}

}
