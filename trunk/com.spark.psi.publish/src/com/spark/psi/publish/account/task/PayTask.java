package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class PayTask extends SimpleTask {

	private GUID paymentId;//	申请单id
	private String paymentNo;//	申请单编号
	public PayTask(GUID paymentId, String paymentNo) {
		super();
		this.paymentId = paymentId;
		this.paymentNo = paymentNo;
	}

	private Item[] items;
	
	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public GUID getPaymentId() {
		return paymentId;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public class Item
	{
		private GUID id;
		private GUID checkinSheetId;//	入库单id
		private String sheetNo;//	入库单号
		private GUID relevantBillId;//	相关单据Id
		private String relevantBillNo;//	相关单据
		private long checkinDate;//	入库日期
		private double amount;//	付款金额
		private double molingAmount; // 
		public Item(GUID id,GUID checkinSheetId, String sheetNo, GUID relevantBillId, String relevantBillNo, 
				long checkinDate, double amount, double molingAmount) {
			super();
			this.id = id;
			this.checkinSheetId = checkinSheetId;
			this.sheetNo = sheetNo;
			this.relevantBillId = relevantBillId;
			this.relevantBillNo = relevantBillNo;
			this.checkinDate = checkinDate;
			this.amount = amount;
			this.molingAmount = molingAmount;
		}
		
		public double getMolingAmount() {
			return molingAmount;
		}

		public void setMolingAmount(double molingAmount) {
			this.molingAmount = molingAmount;
		}

		public void setId(GUID id) {
			this.id = id;
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
		public GUID getId() {
			return id;
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

		
	}
}
