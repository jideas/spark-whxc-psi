package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.ReceiptType;

/**
 * �տ�Task
 */
public class CreateReceiptTask extends SimpleTask {
	private GUID id;//	��ʶ
	private String partnerName;//	�տ����
	private GUID partnerId;//	�տ����
	private String receiptMode;//	�տʽ
	private long receiptDate;//	�տ�����
	private double amount;//	�ܽ��
	private String remark;//	��ע
	private String receiptType;//	�տ�����

	private Item[] items;
	
	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public GUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

	public String getReceiptMode() {
		return receiptMode;
	}

	public void setReceiptMode(String receiptMode) {
		this.receiptMode = receiptMode;
	}

	public long getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(long receiptDate) {
		this.receiptDate = receiptDate;
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

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public class Item
	{
		private GUID checkoutSheetId;//	���ⵥid
		private String sheetNo;//	���ⵥ��
		private GUID relevantBillId;//	��ص���Id
		private String relevantBillNo;//	��ص���
		private long checkoutDate;//	��������
		private double amount;//	������
		public Item(GUID checkoutSheetId, String sheetNo, GUID relevantBillId, String relevantBillNo, long checkoutDate, double amount) {
			super();
			this.checkoutSheetId = checkoutSheetId;
			this.sheetNo = sheetNo;
			this.relevantBillId = relevantBillId;
			this.relevantBillNo = relevantBillNo;
			this.checkoutDate = checkoutDate;
			this.amount = amount;
		}
		
		
		
		public void setCheckoutSheetId(GUID checkoutSheetId) {
			this.checkoutSheetId = checkoutSheetId;
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



		public void setCheckoutDate(long checkoutDate) {
			this.checkoutDate = checkoutDate;
		}



		public void setAmount(double amount) {
			this.amount = amount;
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
		public long getCheckoutDate() {
			return checkoutDate;
		}
		public double getAmount() {
			return amount;
		}
		
	}
}
