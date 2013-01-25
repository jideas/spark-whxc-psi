package com.spark.psi.publish.inventory.checkin.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.ReceiptType;

public class LoadReceiptingInventorySheetKey {

	private GUID sheetId;

	private ReceiptType receiptType;
	private PaymentType paymentType;

	public GUID getSheetId() {
		return sheetId;
	}

	public LoadReceiptingInventorySheetKey(GUID sheetId, ReceiptType receiptType) {
		this.sheetId = sheetId;
		this.receiptType = receiptType;
	}

	public LoadReceiptingInventorySheetKey(GUID sheetId, PaymentType paymentType) {
		this.sheetId = sheetId;
		this.paymentType = paymentType;
	}

	public ReceiptType getReceiptType() {
		return receiptType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}
}
