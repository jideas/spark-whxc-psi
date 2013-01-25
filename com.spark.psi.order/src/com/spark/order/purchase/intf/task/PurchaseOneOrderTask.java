package com.spark.order.purchase.intf.task;

import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;
import com.spark.order.purchase.intf.entity.PurchaseOrder;

/**
 * <p>独立下单task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-12-9
 */
public class PurchaseOneOrderTask extends SimpleTask implements ITaskResult{
	private final List<PurchaseOrder> orders;
	public Map<GUID, GUID> buyRECID;
	public PurchaseOneOrderTask(List<PurchaseOrder> orders) {
		this.orders = orders;
	}
	public List<PurchaseOrder> getOrders() {
		return orders;
	}
	public boolean isSucceed() {
		return true;
	}

	public int lenght() {
		return orders.size();
	}

}
