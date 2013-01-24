package com.spark.oms.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.oms.publish.order.entity.OrderServiceInfo;

/**
 * 处理更新orderService
 * 本方法适用于订单的变更，续单对已有订单服务的影响。
 * 原因：重新保存原有订单可以实现，但相对效率较低，处理复杂。
 */
public class UpdateOrderServiceTask  extends SimpleTask{
	private OrderServiceInfo orderServiceInfo;

	public OrderServiceInfo getOrderServiceInfo() {
		return orderServiceInfo;
	}

	public void setOrderServiceInfo(OrderServiceInfo orderServiceInfo) {
		this.orderServiceInfo = orderServiceInfo;
	}
	
}
