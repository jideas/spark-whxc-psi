package com.spark.order.entityTo;

import java.util.List;

import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;

public class OrderListEntityImpl2 extends OrderListEntity{

//	protected double orderAmount; //�������
//	
//	protected double returnAmount; //�˻����
	
	public OrderListEntityImpl2(List<OrderItem> dataList, int totalCount) {
		super(dataList, totalCount);
	}

	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @param returnAmount the returnAmount to set
	 */
	public void setReturnAmount(double returnAmount) {
		this.returnAmount = returnAmount;
	}
}
