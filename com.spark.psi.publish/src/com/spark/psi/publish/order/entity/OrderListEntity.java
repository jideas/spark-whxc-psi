package com.spark.psi.publish.order.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

/**
 * 
 * <p>�����б����</p>
 *


 *
 
 
 */
public class OrderListEntity extends ListEntity<OrderItem>{

	protected double orderAmount; //�������
	
	protected double returnAmount; //�˻����
	
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
