package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementLog;

public class JointSettlementLogImpl implements JointSettlementLog {

	private GUID id;// ��ʶ
	private GUID sheetId;
	private GUID paymentId;// ���id
	private String paymentNo;
	private double amount;// ���
	private double paidAmount;// �Ѹ����
	private double molingAmount;// Ĩ����
	private GUID creatorId; 
	private String creator;
	private long createDate;
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getSheetId() {
		return sheetId;
	}
	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}
	public GUID getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(GUID paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getMolingAmount() {
		return molingAmount;
	}
	public void setMolingAmount(double molingAmount) {
		this.molingAmount = molingAmount;
	}
	
}
