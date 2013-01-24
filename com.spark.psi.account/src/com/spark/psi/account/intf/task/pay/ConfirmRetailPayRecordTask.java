package com.spark.psi.account.intf.task.pay;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * <p>���������˻������¼��¼ȷ������Ϣ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-5-24
 */
public class ConfirmRetailPayRecordTask extends SimpleTask {

	private GUID employeeId;

	public ConfirmRetailPayRecordTask(GUID employeeId) {
		super();
		this.employeeId = employeeId;
	}

	public GUID getEmployeeId() {
		return employeeId;
	}
}
