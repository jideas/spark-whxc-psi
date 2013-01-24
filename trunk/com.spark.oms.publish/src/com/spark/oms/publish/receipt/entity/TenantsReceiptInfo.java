package com.spark.oms.publish.receipt.entity;

import com.jiuqi.dna.core.type.GUID;
/**
 * �⻧�ɿ������Ϣ
 *
 */

public class TenantsReceiptInfo {
	/**
	 * �⻧��ʶ
	 */
	private GUID tenantsId;
	
	/**
	 * ���
	 */
	private String tenantsNo;
	
	/**
	 * �⻧���
	 */
	private String shortName;
	
	/**
	 * �⻧ȫ��
	 */
	private String name;
	
	/**
	 * ���տ���
	 */
	private double unPayAmount;
	
	/**
	 * ����˻����
	 */
	private double leaseAccountBalance;
	
	/**
	 * �����˻����
	 */
	private double pieceAccountBalance;
	
	/**
	 * �ͻ�����
	 */
	private String saleManagerName;
	
	/**
	 * �˿
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
