package com.spark.psi.publish.order.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;


/**
 * 
 * <p>交易记录列表</p>
 * 查询供应商交易记录
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 周利均
 * @version 2012-2-22
 */
public class TradingRecordListEntity extends ListEntity<OrderItem>{

	protected double totalAmount; //交易总金额
	
	protected double orderAmount;  //订单金额
	
	protected double returnAmount;  //退货金额
	
	protected int orderCount;	//订单次数
	
	protected int returnCount;  //退货次数
	
	public TradingRecordListEntity(List<OrderItem> dataList, int totalCount) {
		super(dataList, totalCount);
	}

	public double getTotalAmount(){
    	return totalAmount;
    }

	public double getOrderAmount(){
    	return orderAmount;
    }

	public double getReturnAmount(){
    	return returnAmount;
    }

	public int getReturnCount(){
    	return returnCount;
    }

	public int getOrderCount() {
		return orderCount;
	}

}
