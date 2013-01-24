/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.order.key.GetOrderListKey.OrderType;

/**
 * ɾ�����۶���,�˻���
 * @author zhoulijun
 *
 */
public final class DeleteSalesOrderTask extends SimpleTask {
	
	private final GUID id;
	private final OrderType orderType;
	
	public DeleteSalesOrderTask(final GUID id, final OrderType orderType){
		this.id = id;
		this.orderType = orderType;
	}

	public GUID getId() {
		return id;
	}
	
	public OrderType getOrderType() {
		return orderType;
	}
	
}
