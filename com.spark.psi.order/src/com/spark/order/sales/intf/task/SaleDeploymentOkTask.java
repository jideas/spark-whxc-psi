package com.spark.order.sales.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>完成出库分配反写标志</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-20
 */
public class SaleDeploymentOkTask extends SimpleTask implements ITaskResult{
	private final GUID billsGuid;
	public int lenght;
	
	/**
	 * @param billsGuid
	 */
	public SaleDeploymentOkTask(GUID billsGuid) {
		this.billsGuid = billsGuid;
	}

	public GUID getBillsGuid() {
		return billsGuid;
	}

	public boolean isSucceed() {
		return 1 == lenght;
	}

	public int lenght() {
		return lenght;
	}
}
