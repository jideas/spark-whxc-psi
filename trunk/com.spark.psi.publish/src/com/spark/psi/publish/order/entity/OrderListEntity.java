package com.spark.psi.publish.order.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

/**
 * 
 * <p>订单列表对象</p>
 *


 *
 
 
 */
public class OrderListEntity extends ListEntity<OrderItem>{

	protected double orderAmount; //订单金额
	
	protected double returnAmount; //退货金额
	
	public OrderListEntity(List<OrderItem> dataList, int totalCount){
	    super(dataList, totalCount);
    }

	public double getOrderAmount(){
    	return orderAmount;
    }


	public double getReturnAmount(){
    	return returnAmount;
    }
	

	
}
