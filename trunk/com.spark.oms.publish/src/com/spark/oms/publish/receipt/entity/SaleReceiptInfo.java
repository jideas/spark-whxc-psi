package com.spark.oms.publish.receipt.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ������Ա�ɿ������Ϣ
 */

public class SaleReceiptInfo {
	/**
	 * ������ԱID
	 */
	private GUID id;
	/**
	 * ���۱��
	 */
	private String saleNo;
	
	/**
	 * ��������
	 */
	private String saleName;
	
	/**
	 * Ӧ�����
	 */
	private double payment;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}
	
}
