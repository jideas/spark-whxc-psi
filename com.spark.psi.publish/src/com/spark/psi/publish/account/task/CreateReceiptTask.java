package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.ReceiptType;

/**
 * 收款Task
 */
public class CreateReceiptTask extends SimpleTask {
	private GUID id;//	标识
	private String partnerName;//	收款对象
	private GUID partnerId;//	收款对象
	private String receiptMode;//	收款方式
	private long receiptDate;//	收款日期
	private double amount;//	总金额
	private String remark;//	备注
	private String receiptType;//	收款类型

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
		private GUID checkoutSheetId;//	出库单id
		private String sheetNo;//	出库单号
		private GUID relevantBillId;//	相关单据Id
		private String relevantBillNo;//	相关单据
		private long checkoutDate;//	出库日期
		private double amount;//	出库金额
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
