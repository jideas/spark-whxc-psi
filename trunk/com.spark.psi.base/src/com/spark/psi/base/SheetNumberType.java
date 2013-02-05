package com.spark.psi.base;

public enum SheetNumberType {

	Sales("XSD", 4), // œ˙ €
	OnlineOrder("WSDD", 4), //
	ProduceOrder("SCDD", 4), //
	Deliver("PSD", 4), //
	DeliverTicket("FHD", 4), //
	SalesReturn("XTD", 4), //
	Purchaes("CGD", 4), //
	PurchaseReturn("CTD", 4), //
	Retail("LSD", 4), //
	RetailReturn("LTD", 4), //
	Checkout("CKD", 4), //
	Checkin("RKD", 4), //
	InventoryCount("PDD", 4), //
	InventoryRefactor("CZD", 4), //
	InventoryAllocate("DBD", 4), //
	ReportLoss("BSD", 4), //
	Receipt("SKD", 4), //
	Payment("FKD", 4), //
	GoodsSerial("", 6, true), //
	BalanceAdjust("TZD", 4), //
	JointSettlement("JSD", 4), //
	GoodsSplit("CFD", 4), //
	OnlineReturn("WTD", 4); //

	private String defaultPrefix;
	private int length;
	private boolean onlyOrderNo;

	private SheetNumberType(String defaultPrefix, int length) {
		this.defaultPrefix = defaultPrefix;
		this.length = length;
		this.onlyOrderNo = false;
	}

	private SheetNumberType(String defaultPrefix, int length,
			boolean onlyOrderNo) {
		this.defaultPrefix = defaultPrefix;
		this.length = length;
		this.onlyOrderNo = onlyOrderNo;
	}

	public boolean isOnlyOrderNo() {
		return onlyOrderNo;
	}

	public String getDefaultPrefix() {
		return this.defaultPrefix;
	}

	public int getLength() {
		return length;
	}
}
