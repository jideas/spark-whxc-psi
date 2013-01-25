/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

package com.spark.order.purchase.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;

/**
 * <p>采购退货明细Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 王天才
 * @version 2011-11-10
 */

public class PurchaseCancelDetTask extends Task<TaskEnum> {
	
	private PurchaseCancelItem entity;
	
	private GUID billsGuid;

	public void setEntity(PurchaseCancelItem entity) {
		this.entity = entity;
	}

	public PurchaseCancelItem getEntity() {
		return entity;
	}

	public void setBillsGuid(GUID billsGuid) {
		this.billsGuid = billsGuid;
	}

	public GUID getBillsGuid() {
		return billsGuid;
	}

}
