package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeletePaymentTask extends SimpleTask {

	private GUID[] ids;

	public DeletePaymentTask(GUID... ids) {
		super();
		this.ids = ids;
	}

	public GUID[] getIds() {
		return ids;
	}
	
}
