package com.spark.psi.publish.base.organization.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EmployeeStatus;

/**
 * 改变员工状态（离职和复职）
 * 
 */
public class ChangeEmployeeStatusTask extends SimpleTask {

	/**
	 * 用户id数组
	 */
	private GUID[] ids;

	/**
	 * UserStatus
	 */
	private EmployeeStatus userstatus;

	/**
	 * 构造函数
	 * 
	 * @param ids
	 * @param userstatus
	 */
	public ChangeEmployeeStatusTask(EmployeeStatus userstatus,GUID... ids) {
		this.ids = ids;
		this.userstatus = userstatus;
	}
	

	/**
	 * @return the ids
	 */
	public GUID[] getIds() {
		return ids;
	}

	/**
	 * @return the userstatus
	 */
	public EmployeeStatus getUserStatus() {
		return userstatus;
	}

}
