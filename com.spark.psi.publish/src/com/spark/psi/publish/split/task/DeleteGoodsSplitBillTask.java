package com.spark.psi.publish.split.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteGoodsSplitBillTask extends SimpleTask {
	private GUID recid;

	public GUID getRecid() {
		return recid;
	}

	public DeleteGoodsSplitBillTask(GUID recid) {
		this.recid = recid;
	}
}
