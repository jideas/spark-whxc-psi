package com.spark.psi.publish.order.task;

import com.spark.psi.publish.DealingsWay;


/**
 * 
 * <p>���۵�ֱ���տ�</p>
 * �������۵�����������տ����


 *
 
 * @version 2012-3-20
 */
public class CreateReceiptRetailOrderTask extends CreateRetailOrderTask{
	/**
	 * ���ʽ
	 */
	private DealingsWay dealingsWay;

	public DealingsWay getDealingsWay(){
    	return dealingsWay;
    }

	public void setDealingsWay(DealingsWay dealingsWay){
    	this.dealingsWay = dealingsWay;
    } 
	
	
}
