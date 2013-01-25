package com.spark.psi.publish.inventory.checkin.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.ReceiptType;

public class GetReceiptingInventorySheetKey {

	private GUID partnerId;

	private ReceiptType receiptType;
	private PaymentType paymentType;

	public GUID getPartnerId() {
		return partnerId;
	}

	public GetReceiptingInventorySheetKey(GUID partnerId, ReceiptType receiptType) {
		this.partnerId = partnerId;
		this.receiptType = receiptType;
	}

	public GetReceiptingInventorySheetKey(GUID partnerId, PaymentType paymentType) {
		this.partnerId = partnerId;
		this.paymentType = paymentType;
	}

	public ReceiptType getReceiptType() {
		return receiptType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}
}
