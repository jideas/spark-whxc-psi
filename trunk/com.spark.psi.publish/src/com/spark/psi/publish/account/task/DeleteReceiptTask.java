package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteReceiptTask extends SimpleTask {

	private GUID[] ids;

	public DeleteReceiptTask(GUID... ids) {
		super();
		this.ids = ids;
	}

	public GUID[] getIds() {
		return ids;
	}
	
}
