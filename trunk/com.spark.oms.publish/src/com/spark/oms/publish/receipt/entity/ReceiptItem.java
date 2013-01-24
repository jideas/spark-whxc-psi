package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 收款支付记录:收支历史
 */
public class ReceiptItem {
	/**
	 * 收款合计
	 */
	private double payAmount;
	
	/**
	 * 退款合计
	 */
	private double refundAmount;
	
	/**
	 * 收退款记录
	 */
	private List<ReceiptInfo> receiptList = new ArrayList<ReceiptInfo>();
	
	public void add(ReceiptInfo receiptInfo){
		receiptList.add(receiptInfo);
	}
	

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public List<ReceiptInfo> getReceiptList() {
		return receiptList;
	}

	public void setReceiptList(List<ReceiptInfo> receiptList) {
		this.receiptList = receiptList;
	}
	
}
