package com.spark.psi.publish.deliver.Event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

public class DeliverExceptionCreateEvent extends Event {

	private GUID id;
	private String billNo;

	public DeliverExceptionCreateEvent(GUID id, String billNo) {
		this.id = id;
		this.billNo = billNo;
	}

	public GUID getId() {
		return id;
	}

	public String getBillNo() {
		return billNo;
	}
}
