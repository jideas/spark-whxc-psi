package com.spark.psi.account.intf.entity.pay;

import com.jiuqi.dna.core.type.GUID;

public class PaymentLogEntity {

	private GUID id;// ��ʶ
	private GUID paymentId;// ���뵥id
	private String paymentNo;// ���뵥���
	private GUID checkinSheetId;// ��ⵥid
	private String sheetNo;// ��ⵥ��
	private GUID relevantBillId;// ��ص���Id
	private String relevantBillNo;// ��ص���
	private long checkinDate;// �������
	private double amount;// ������
	private double molingAmount;// Ĩ����
	private GUID payPersonId;// ������
	private String payPersonName;// ����������
	private long payDate;// ��������
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
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
	public GUID getCheckinSheetId() {
		return checkinSheetId;
	}
	public void setCheckinSheetId(GUID checkinSheetId) {
		this.checkinSheetId = checkinSheetId;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public GUID getRelevantBillId() {
		return relevantBillId;
	}
	public void setRelevantBillId(GUID relevantBillId) {
		this.relevantBillId = relevantBillId;
	}
	public String getRelevantBillNo() {
		return relevantBillNo;
	}
	public void setRelevantBillNo(String relevantBillNo) {
		this.relevantBillNo = relevantBillNo;
	}
	public long getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(long checkinDate) {
		this.checkinDate = checkinDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getMolingAmount() {
		return molingAmount;
	}
	public void setMolingAmount(double molingAmount) {
		this.molingAmount = molingAmount;
	}
	public GUID getPayPersonId() {
		return payPersonId;
	}
	public void setPayPersonId(GUID payPersonId) {
		this.payPersonId = payPersonId;
	}
	public String getPayPersonName() {
		return payPersonName;
	}
	public void setPayPersonName(String payPersonName) {
		this.payPersonName = payPersonName;
	}
	public long getPayDate() {
		return payDate;
	}
	public void setPayDate(long payDate) {
		this.payDate = payDate;
	}

	
}
