/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task.sale
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-19       莫迪        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task.sale
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-19       莫迪        
 * ============================================================*/

package com.spark.order.sales.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.TaskEnum;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-19
 */

public class SaleOrdDetTask extends Task<TaskEnum> {

	public com.spark.order.sales.intf.entity.SaleOrderItem entity;
	public GUID recid;
	public GUID billsGuid;//根据订单编号闪明细专用
}
