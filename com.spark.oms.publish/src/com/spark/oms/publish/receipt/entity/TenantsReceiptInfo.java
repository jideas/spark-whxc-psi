package com.spark.oms.publish.receipt.entity;

import com.jiuqi.dna.core.type.GUID;
/**
 * 租户缴款基本信息
 *
 */

public class TenantsReceiptInfo {
	/**
	 * 租户标识
	 */
	private GUID tenantsId;
	
	/**
	 * 编号
	 */
	private String tenantsNo;
	
	/**
	 * 租户简称
	 */
	private String shortName;
	
	/**
	 * 租户全称
	 */
	private String name;
	
	/**
	 * 待收款金额
	 */
	private double unPayAmount;
	
	/**
	 * 租金账户金额
	 */
	private double leaseAccountBalance;
	
	/**
	 * 计量账户金额
	 */
	private double pieceAccountBalance;
	
	/**
	 * 客户经理
	 */
	private String saleManagerName;
	
	/**
	 * 退款单
	 * @return
	 */
	public GUID receiptRECID;
	
	
	
	public GUID getReceiptRECID() {
		return receiptRECID;
	}

	public void setReceiptRECID(GUID receiptRECID) {
		this.receiptRECID = receiptRECID;
	}

	public String getTenantsNo() {
		return tenantsNo;
	}

	public void setTenantsNo(String tenantsNo) {
		this.tenantsNo = tenantsNo;
	}


	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnPayAmount() {
		return unPayAmount;
	}

	public void setUnPayAmount(double unPayAmount) {
		this.unPayAmount = unPayAmount;
	}

	public double getLeaseAccountBalance() {
		return leaseAccountBalance;
	}

	public void setLeaseAccountBalance(double leaseAccountBalance) {
		this.leaseAccountBalance = leaseAccountBalance;
	}

	public double getPieceAccountBalance() {
		return pieceAccountBalance;
	}

	public void setPieceAccountBalance(double pieceAccountBalance) {
		this.pieceAccountBalance = pieceAccountBalance;
	}

	public String getSaleManagerName() {
		return saleManagerName;
	}

	public void setSaleManagerName(String saleManagerName) {
		this.saleManagerName = saleManagerName;
	}
	
}
