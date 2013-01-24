package com.spark.oms.publish.receipt.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 销售人员缴款基本信息
 */

public class SaleReceiptInfo {
	/**
	 * 销售人员ID
	 */
	private GUID id;
	/**
	 * 销售编号
	 */
	private String saleNo;
	
	/**
	 * 销售名称
	 */
	private String saleName;
	
	/**
	 * 应交金额
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
