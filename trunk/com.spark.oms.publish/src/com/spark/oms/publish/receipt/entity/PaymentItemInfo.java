package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

public class PaymentItemInfo {
	/**
	 * ����ϼ�
	 */
	private double totalIncome;
	
	/**
	 * ֧���ϼ�
	 */
	private double totalOutlay;
	
	/**
	 * ��֧��¼
	 */
	private List<PaymentInfo> paymentList = new ArrayList<PaymentInfo>();

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public double getTotalOutlay() {
		return totalOutlay;
	}

	public void setTotalOutlay(double totalOutlay) {
		this.totalOutlay = totalOutlay;
	}

	public List<PaymentInfo> getPaymentList() {
		return paymentList;
	}

	public void add(PaymentInfo info){
		paymentList.add(info);
	}
	
	

}
