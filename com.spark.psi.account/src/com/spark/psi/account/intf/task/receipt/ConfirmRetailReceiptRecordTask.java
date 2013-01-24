package com.spark.psi.account.intf.task.receipt;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * <p>更新零售收款纪录纪录确认人信息</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

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
