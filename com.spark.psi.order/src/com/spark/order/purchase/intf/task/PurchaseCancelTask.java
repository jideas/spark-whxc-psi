/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

package com.spark.order.purchase.intf.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.order.intf.task.ITaskResult;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;

/**
 * <p>采购退货Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 王天才
 * @version 2011-11-10
 */

public class PurchaseCancelTask extends Task<TaskEnum> implements ITaskResult{
	public int lenght;
	private PurchaseCancel entity;
	private List<PurchaseCancelItem> list;

	public void setEntity(PurchaseCancel entity) {
		this.entity = entity;
	}

	public PurchaseCancel getEntity() {
		return entity;
	}

	public void setList(List<PurchaseCancelItem> list) {
		this.list = list;
	}

	public List<PurchaseCancelItem> getList() {
		return list;
	}

	public boolean isSucceed() {
		return 1 == lenght;
	}

	public int lenght() {
		return lenght;
	}
}
