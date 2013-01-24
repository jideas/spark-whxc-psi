package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

public class GetReceiptingCheckOutSheetKey extends SimpleTask {

	private GUID partnerId;

	private CheckingOutType type;

	public GetReceiptingCheckOutSheetKey(GUID partnerId, CheckingOutType type) {
		this.partnerId = partnerId;
		this.type = type;
	}

	public GUID getPartnerId() {
		return partnerId;
	}

	public CheckingOutType getType() {
		return type;
	}
}
