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
import com.spark.order.intf.task.ITaskResult;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.sales.intf.entity.SaleOrder;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-19
 */

public class SaleOrderTask extends Task<TaskEnum> implements ITaskResult{

	public com.spark.order.sales.intf.entity.SaleOrderInfo entity;
	public SaleOrder order;//新建销售订单专用
	public GUID recid;
	
	private boolean succeed = true;
	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	public boolean isSucceed() {
		return succeed;
	}
	public int lenght() {
		return succeed?1:0;
	}
}
