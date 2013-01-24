package com.spark.psi.account.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PaymentType;

/**
 * 
 * <p>付款生效事件</p>
 */
public class PaymentEffectiveEvent extends Event{

	/**
	 * 入库单ID
	 */
	private GUID billsId;
	private double amount;
	private PaymentType paymentType;

	public GUID getBillsId() {
		return billsId;
	}

	public PaymentEffectiveEvent(GUID billsId, double amount,PaymentType paymentType) {
		super();
		this.billsId = billsId;
		this.amount = amount;
		this.paymentType = paymentType;
	}

	public double getAmount() {
		return amount;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}
	
    
    
}
