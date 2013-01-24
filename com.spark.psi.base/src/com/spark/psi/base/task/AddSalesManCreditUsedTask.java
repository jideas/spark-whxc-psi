package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * 
 * <p>�ۼ����۾��������õ����ö�ȼ��ͻ�����</p>
 *


 *
 
 * @version 2012-5-17
 */
public class AddSalesManCreditUsedTask extends SimpleTask{
	
	/**
	 * ���ö��
	 */
	private double amount;
	
	/**
	 * �ͻ���
	 */
	private int count;
	
	public AddSalesManCreditUsedTask(double amount){
	    this.amount = amount;
	    this.count = 1;
    }
	
	public AddSalesManCreditUsedTask(double amount,int count){
		this.amount = amount;
		this.count = count;
    }

	public double getAmount(){
    	return amount;
    }

	public void setAmount(double amount){
    	this.amount = amount;
    }

	public int getCount(){
    	return count;
    }

	public void setCount(int count){
    	this.count = count;
    }
	
}