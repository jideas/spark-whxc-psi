package com.spark.oms.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.oms.publish.order.entity.OrderServiceInfo;

/**
 * �������orderService
 * �����������ڶ����ı�������������ж��������Ӱ�졣
 * ԭ�����±���ԭ�ж�������ʵ�֣������Ч�ʽϵͣ������ӡ�
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
