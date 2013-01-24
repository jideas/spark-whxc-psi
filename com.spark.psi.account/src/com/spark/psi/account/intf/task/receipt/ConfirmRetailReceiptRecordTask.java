package com.spark.psi.account.intf.task.receipt;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * <p>���������տ��¼��¼ȷ������Ϣ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-5-24
 */
public class ConfirmRetailReceiptRecordTask extends SimpleTask {

	private GUID employeeId;

	public ConfirmRetailReceiptRecordTask(GUID employeeId) {
		super();
		this.employeeId = employeeId;
	}

	public GUID getEmployeeId() {
		return employeeId;
	}
}
