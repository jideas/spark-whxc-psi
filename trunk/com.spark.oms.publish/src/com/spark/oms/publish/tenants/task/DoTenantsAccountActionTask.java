package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.type.GUID;

/**
 * Ӧ��Ϊ�ڲ��ӿ�
 * �޸��⻧���˻�����������Ϣ
 */
public class DoTenantsAccountActionTask {
	
	//�⻧ID
	private GUID teantsId ;
	
	//�����˻����
	private double leaseAccountBalance;
	
	//�����˻����
	private double pieceAccountBalance;
	
	//�ۼ��տ���
	private double receiptAmount;
	
	//�ۼƿ�Ʊ���
	private double invoiceAmount;
	
	//��������
	private int ServeMonth;
	
	//�״ζ���
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
