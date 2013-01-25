package com.spark.psi.publish.order.task;

import com.spark.psi.publish.DealingsWay;


/**
 * 
 * <p>零售单直接收款</p>
 * 创建零售单，并且完成收款操作


 *
 
 * @version 2012-3-20
 */
public class CreateReceiptRetailOrderTask extends CreateRetailOrderTask{
	/**
	 * 付款方式
	 */
	private DealingsWay dealingsWay;

	public DealingsWay getDealingsWay(){
    	return dealingsWay;
    }

	public void setDealingsWay(DealingsWay dealingsWay){
    	this.dealingsWay = dealingsWay;
    } 
	
	
}
