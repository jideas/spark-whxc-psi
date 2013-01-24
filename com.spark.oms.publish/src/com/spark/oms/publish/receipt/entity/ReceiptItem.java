package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * �տ�֧����¼:��֧��ʷ
 */
public class ReceiptItem {
	/**
	 * �տ�ϼ�
	 */
	private double payAmount;
	
	/**
	 * �˿�ϼ�
	 */
	private double refundAmount;
	
	/**
	 * ���˿��¼
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
