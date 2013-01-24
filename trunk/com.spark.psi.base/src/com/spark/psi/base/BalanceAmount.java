package com.spark.psi.base;

/**
 * 
 * <p>往来金额</p>
 * 客户 对应 应收
 * 供应商 对应 应付


 *
 
 * @version 2012-3-9
 */
public class BalanceAmount{
	
	private double DueAmount;

	public double getDueAmount() {
		return DueAmount;
	}

	public void setDueAmount(double dueAmount) {
		DueAmount = dueAmount;
	}
		
}
