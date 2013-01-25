package com.spark.order.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;

public class OrderListEntityImpl extends OrderListEntity{

//	protected double orderAmount; //订单金额
//	
//	protected double returnAmount; //退货金额
	
	public OrderListEntityImpl(List<OrderItem> dataList, int totalCount) {
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
