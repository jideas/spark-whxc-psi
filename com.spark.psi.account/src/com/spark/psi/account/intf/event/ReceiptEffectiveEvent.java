package com.spark.psi.account.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ReceiptType;

/**
 * 
 * <p>�տ���Ч�¼�</p>
 */
public class ReceiptEffectiveEvent extends Event{

	/**
	 * ���ⵥID
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
