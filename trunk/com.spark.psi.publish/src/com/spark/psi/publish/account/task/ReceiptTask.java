package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class ReceiptTask extends SimpleTask {
	
	private GUID receiptsId;//	֪ͨ��id
	private String receiptNo;//	֪ͨ�����
	public ReceiptTask(GUID receiptsId, String receiptNo) {
		super();
		this.receiptsId = receiptsId;
		this.receiptNo = receiptNo;
	}

	private Item[] items;
	public Item[] getItems() {
		return items;
	}
	public void setItems(Item[] items) {
		this.items = items;
	}
	public GUID getReceiptsId() {
		return receiptsId;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public class Item
	{
		private GUID id;//��ʶ
		private GUID checkoutSheetId;//	���ⵥid
		private String sheetNo;//	���ⵥ��
		private GUID relevantBillId;//	��ص���Id
		private String relevantBillNo;//	��ص���
		private long checkinDate;//	��������
		private double amount;//	�տ���
		private double molingAmount;//	�տ���
		public double getMolingAmount() {
			return molingAmount;
		}
		public Item(GUID id, GUID checkoutSheetId, String sheetNo, GUID relevantBillId, String relevantBillNo, long checkinDate, double amount,double molingAmount) {
			super();
			this.id = id;
			this.checkoutSheetId = checkoutSheetId;
			this.sheetNo = sheetNo;
			this.relevantBillId = relevantBillId;
			this.relevantBillNo = relevantBillNo;
			this.checkinDate = checkinDate;
			this.amount = amount;
			this.molingAmount = molingAmount;
		}
		public GUID getId() {
			return id;
		}
		public GUID getCheckoutSheetId() {
			return checkoutSheetId;
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
