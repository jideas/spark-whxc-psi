package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.type.GUID;

/**
 * 应该为内部接口
 * 修改租户的账户余额等事务信息
 */
public class DoTenantsAccountActionTask {
	
	//租户ID
	private GUID teantsId ;
	
	//租赁账户金额
	private double leaseAccountBalance;
	
	//计量账户金额
	private double pieceAccountBalance;
	
	//累计收款金额
	private double receiptAmount;
	
	//累计开票金额
	private double invoiceAmount;
	
	//服务期限
	private int ServeMonth;
	
	//首次订单
	private long firstSignOrderDate;

	public GUID getTeantsId() {
		return teantsId;
	}

	public void setTeantsId(GUID teantsId) {
		this.teantsId = teantsId;
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

	public double getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public int getServeMonth() {
		return ServeMonth;
	}

	public void setServeMonth(int serveMonth) {
		ServeMonth = serveMonth;
	}

	public long getFirstSignOrderDate() {
		return firstSignOrderDate;
	}

	public void setFirstSignOrderDate(long firstSignOrderDate) {
		this.firstSignOrderDate = firstSignOrderDate;
	}
	
}
