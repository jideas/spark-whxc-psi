/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 *�޸Ŀͻ�����
 
 * 
 */
public class UpdateCustomerCreditTask extends SimpleTask {

	
	private double creditAmount;// ���ö��
	
	private int creditDay;// ����
	
	private int remindDay;// Ԥ������
	
	private GUID[] customers;
	
	public UpdateCustomerCreditTask(double creditAmount,int creditDay,int remindDay,GUID...customers){
		this.customers = customers;
		this.creditAmount = creditAmount;
		this.remindDay = remindDay;
		this.creditDay = creditDay;
	}
	
	public GUID[] getCustomers(){
    	return customers;
    }



	public void setCustomers(GUID[] customers){
    	this.customers = customers;
    }



	public double getCreditAmount(){
		return creditAmount;
	}
	
	public void setCreditAmount(double creditAmount){
		this.creditAmount = creditAmount;
	}
	
	public int getAccountPeriod(){
		return creditDay;
	}
	
	public void setCreditDay(int creditDay){
		this.creditDay = creditDay;
	}
	
	public int getRemindDay(){
		return remindDay;
	}
	
	public void setRemindDay(int remindDay){
		this.remindDay = remindDay;
	}
}
