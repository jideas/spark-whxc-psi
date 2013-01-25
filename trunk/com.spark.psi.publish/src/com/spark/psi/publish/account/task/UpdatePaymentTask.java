package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PaymentType;

public class UpdatePaymentTask extends SimpleTask {

	private GUID id;//	��ʶ
//	private String partnerName;//	�������
//	private GUID partnerId;//	�������
	private PaymentType paymentType;//	��������
//	private String reason;//	����ԭ��
	private long payDate;//	��������
	private double amount;//	�ܽ��
	private String remark;//	��ע
	private Item[] items;
	private String dealingsWay;
	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

//	public String getPartnerName() {
//		return partnerName;
//	}
//
//	public void setPartnerName(String partnerName) {
//		this.partnerName = partnerName;
//	}
//
//	public GUID getPartnerId() {
//		return partnerId;
//	}
//
//	public void setPartnerId(GUID partnerId) {
//		this.partnerId = partnerId;
//	}
//
	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
//
//	public String getReason() {
//		return reason;
//	}
//
//	public void setReason(String reason) {
//		this.reason = reason;
//	}
//
	public long getPayDate() {
		return payDate;
	}

	public void setPayDate(long payDate) {
		this.payDate = payDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public void setDealingsWay(String dealingsWay) {
		this.dealingsWay = dealingsWay;
	}

	public String getDealingsWay() {
		return dealingsWay;
	}

	public class Item
	{
		private GUID checkinSheetId;//	��ⵥid
		private String sheetNo;//	��ⵥ��
		private GUID relevantBillId;//	��ص���Id
		private String relevantBillNo;//	��ص���
		private long checkinDate;//	�������
		private double amount;//	�����
		private double askAmount;//	������
		private double molingAmount;//	Ĩ����
		public Item(GUID checkinSheetId, String sheetNo, GUID relevantBillId, String relevantBillNo, long checkinDate, double amount,
				double askAmount, double molingAmount) {
			super();
			this.checkinSheetId = checkinSheetId;
			this.sheetNo = sheetNo;
			this.relevantBillId = relevantBillId;
			this.relevantBillNo = relevantBillNo;
			this.checkinDate = checkinDate;
			this.amount = amount;
			this.askAmount = askAmount;
			this.molingAmount = molingAmount;
		}
		
		public void setCheckinSheetId(GUID checkinSheetId) {
			this.checkinSheetId = checkinSheetId;
		}

		public void setSheetNo(String sheetNo) {
			this.sheetNo = sheetNo;
		}
		public void setRelevantBillId(GUID relevantBillId) {
			this.relevantBillId = relevantBillId;
		}
		public void setRelevantBillNo(String relevantBillNo) {
			this.relevantBillNo = relevantBillNo;
		}
		public void setCheckinDate(long checkinDate) {
			this.checkinDate = checkinDate;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public void setAskAmount(double askAmount) {
			this.askAmount = askAmount;
		}
		public void setMolingAmount(double molingAmount) {
			this.molingAmount = molingAmount;
		}
		public GUID getCheckinSheetId() {
			return checkinSheetId;
		}
		public String getSheetNo() {
			return sheetNo;
		}
		public GUID getRelevantBillId() {
			return relevantBillId;
		}
		public String getRelevantBillNo() {
			return relevantBillNo;
		}
		public long getCheckinDate() {
			return checkinDate;
		}
		public double getAmount() {
			return amount;
		}
		public double getAskAmount() {
			return askAmount;
		}
		public double getMolingAmount() {
			return molingAmount;
		}

		
	}
}
