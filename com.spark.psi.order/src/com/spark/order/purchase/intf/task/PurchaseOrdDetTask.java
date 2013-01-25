/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-14       莫迪        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-14       莫迪        
 * ============================================================*/

package com.spark.order.purchase.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.TaskEnum;

/**
 * <p>采购明细</p>
 *<link>com.spark.bills.service.BuyOrdDetService</link>
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-14
 */

public class PurchaseOrdDetTask extends Task<TaskEnum> {

	public com.spark.order.purchase.intf.entity.PurchaseOrderItem entity;
	public GUID recid;
	public GUID billsGuid;

}
