/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-15       莫迪        
 * ============================================================*/

package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancel;

/**
 * <p>更新退货单状态Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 王天才
 * @version 2011-11-15
 */

public class ModifyCancelStatusTask extends Task<TaskEnum> {

	private PurchaseCancel entity;
	private boolean done;

	public void setEntity(PurchaseCancel entity) {
		this.entity = entity;
	}

	public PurchaseCancel getEntity() {
		return entity;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isDone() {
		return done;
	}
	
}
