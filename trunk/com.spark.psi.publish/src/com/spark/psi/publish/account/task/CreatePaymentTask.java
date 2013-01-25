package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class CreatePaymentTask extends SimpleTask {

	private GUID id;//	标识
	private String partnerName;//	付款对象
	private GUID partnerId;//	付款对象
	private String paymentType;//	付款类型
	private long payDate;//	付款日期
	private double amount;//	总金额
	private String remark;//	备注
	private String dealingsWay;//	付款方式	char	2


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



	public String getPaymentType() {
		return paymentType;
	}



	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}



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
		private GUID checkinSheetId;//	入库单id
		private String sheetNo;//	入库单号
		private GUID relevantBillId;//	相关单据Id
		private String relevantBillNo;//	相关单据
		private long checkinDate;//	入库日期
		private double amount;//	入库金额
		private double askAmount;//	申请金额
		private double molingAmount;//	抹零金额
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
