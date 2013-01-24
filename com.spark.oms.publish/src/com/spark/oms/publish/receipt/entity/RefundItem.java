package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 退款条目:租户退款
 */
public class RefundItem {

	/**
	 * 租金账户金额
	 */
	private double totalLeaseAccountBalance;
	
	/**
	 * 计量账户金额
	 */
	private double totalPieceAccountBalance;
	
	/**
	 * 客户退款列表
	 */
	private List<TenantsReceiptInfo> tenantsReceiptList = new ArrayList<TenantsReceiptInfo>();
	
	public void add(TenantsReceiptInfo tenantsReceiptInfo){
		tenantsReceiptList.add(tenantsReceiptInfo);
	}
	

	public double getTotalLeaseAccountBalance() {
		return totalLeaseAccountBalance;
	}

	public void setTotalLeaseAccountBalance(double totalLeaseAccountBalance) {
		this.totalLeaseAccountBalance = totalLeaseAccountBalance;
	}

	public double getTotalPieceAccountBalance() {
		return totalPieceAccountBalance;
	}

	public void setTotalPieceAccountBalance(double totalPieceAccountBalance) {
		this.totalPieceAccountBalance = totalPieceAccountBalance;
	}

	public List<TenantsReceiptInfo> getTenantsReceiptList() {
		return tenantsReceiptList;
	}

	public void setTenantsReceiptList(List<TenantsReceiptInfo> tenantsReceiptList) {
		this.tenantsReceiptList = tenantsReceiptList;
	}
	
}
