package com.spark.psi.base.internal.entity;

import com.spark.psi.base.CustomerCretid;

public class CustomerCreditImpl implements CustomerCretid{
	/**
	 * ���ö��
	 */
	private double CreditAmount ;
	/**
	 * ����
	 */
	private double CreditDay;
	/**
	 * �ѳ����ö�Ƚ��
	 */
	private double OverCreditAmount ;
	/**
	 * �ѳ���������
	 */
	private double OverCreditDay;
	/**
	 * ����Ԥ������
	 */
	private double RemindDay;
	/**
	 * ����Ԥ�����
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
