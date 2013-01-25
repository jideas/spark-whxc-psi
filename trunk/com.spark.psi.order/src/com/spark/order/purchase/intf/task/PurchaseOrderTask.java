/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

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
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;

/**
 * <p>TODO 类描述</p>
 *<link>com.spark.bills.service.BuyOrderService</link>
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-10
 */

public class PurchaseOrderTask extends Task<TaskEnum> implements ITaskResult{
	public int lenght;
	public com.spark.order.purchase.intf.entity.PurchaseOrderInfo entity;
	/**
	 * 采购订单明细
	 */
	public List<PurchaseOrderItem> dets;
	public GUID recid;
	public boolean isSucceed() {
		return 1 == lenght;
	}
	public int lenght() {
		return lenght;
	}
}
