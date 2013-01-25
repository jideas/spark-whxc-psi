package com.spark.psi.publish.base.organization.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EmployeeStatus;

/**
 * �ı�Ա��״̬����ְ�͸�ְ��
 * 
 */
public class ChangeEmployeeStatusTask extends SimpleTask {

	/**
	 * �û�id����
	 */
	private GUID[] ids;

	/**
	 * UserStatus
	 */
	private EmployeeStatus userstatus;

	/**
	 * ���캯��
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
