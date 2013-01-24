package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

public class LoadReceiptingCheckOutSheetKey extends SimpleTask {

	private GUID sheetId;

	private CheckingOutType type;

	public LoadReceiptingCheckOutSheetKey(GUID sheetId, CheckingOutType type) {
		this.sheetId = sheetId;
		this.type = type;
	}

	public GUID getSheetId() {
		return sheetId;
	}

	public CheckingOutType getType() {
		return type;
	}
}
