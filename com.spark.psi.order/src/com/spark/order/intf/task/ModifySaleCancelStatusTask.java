/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-23       莫迪        
 * ============================================================*/

package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.sales.intf.entity.SaleCancel;

/**
 * <p>销售退货流程状态修改TASK</p>
 */

public class ModifySaleCancelStatusTask extends Task<TaskEnum> {

	private SaleCancel entity;
	private boolean done;

	public void setEntity(SaleCancel entity) {
		this.entity = entity;
	}

	public SaleCancel getEntity() {
		return entity;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isDone() {
		return done;
	}
}
