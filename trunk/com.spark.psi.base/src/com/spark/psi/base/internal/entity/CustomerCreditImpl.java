package com.spark.psi.base.internal.entity;

import com.spark.psi.base.CustomerCretid;

public class CustomerCreditImpl implements CustomerCretid{
	/**
	 * 信用额度
	 */
	private double CreditAmount ;
	/**
	 * 账期
	 */
	private double CreditDay;
	/**
	 * 已超信用额度金额
	 */
	private double OverCreditAmount ;
	/**
	 * 已超账期天数
	 */
	private double OverCreditDay;
	/**
	 * 账期预警天数
	 */
	private double RemindDay;
	/**
	 * 账期预警金额
	 */
	private double RemindingAmount;
	
	public double getCreditAmount(){
    	return CreditAmount;
    }
	public double getAccountPeriod(){
    	return CreditDay;
    }
	public double getOverCreditAmount(){
    	return OverCreditAmount;
    }
	public double getOverCreditDay(){
    	return OverCreditDay;
    }
	public double getRemindDay(){
    	return RemindDay;
    }
	public double getRemindingAmount(){
    	return RemindingAmount;
    }

	
}
