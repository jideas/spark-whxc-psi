package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * �˿���Ŀ:�⻧�˿�
 */
public class RefundItem {

	/**
	 * ����˻����
	 */
	private double totalLeaseAccountBalance;
	
	/**
	 * �����˻����
	 */
	private double totalPieceAccountBalance;
	
	/**
	 * �ͻ��˿��б�
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
