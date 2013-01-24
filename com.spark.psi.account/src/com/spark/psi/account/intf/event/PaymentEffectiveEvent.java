package com.spark.psi.account.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PaymentType;

/**
 * 
 * <p>������Ч�¼�</p>
 */
public class PaymentEffectiveEvent extends Event{

	/**
	 * ��ⵥID
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
