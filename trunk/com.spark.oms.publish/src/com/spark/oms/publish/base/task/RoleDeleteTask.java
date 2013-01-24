package com.spark.oms.publish.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

public class RoleDeleteTask extends SimpleTask {
	
	/**
	 * ½ÇÉ«Code
	 * @return
	 */
	private String roleCode;
	
	private int operatingstatus = 0;

	public int getOperatingstatus() {
		return operatingstatus;
	}

	public void setOperatingstatus(int operatingstatus) {
		this.operatingstatus = operatingstatus;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}