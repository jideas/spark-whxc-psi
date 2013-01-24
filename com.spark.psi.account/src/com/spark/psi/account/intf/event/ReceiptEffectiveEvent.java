package com.spark.psi.account.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ReceiptType;

/**
 * 
 * <p>收款生效事件</p>
 */
public class ReceiptEffectiveEvent extends Event{

	/**
	 * 出库单ID
	 */
	private GUID billsId;
	
	private ReceiptType receiptType;

	public GUID getBillsId() {
		return billsId;
	}

	public ReceiptEffectiveEvent(GUID billsId,ReceiptType receiptType) {
		super();
		this.billsId = billsId;
		this.receiptType = receiptType;
	}

	public ReceiptType getReceiptType() {
		return receiptType;
	}

	
    
    
}
