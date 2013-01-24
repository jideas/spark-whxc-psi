package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * 
 * <p>累计销售经理已设置的信用额度及客户数量</p>
 *


 *
 
 * @version 2012-5-17
 */
public class AddSalesManCreditUsedTask extends SimpleTask{
	
	/**
	 * 信用额度
	 */
	private double amount;
	
	/**
	 * 客户数
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