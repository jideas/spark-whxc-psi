package com.spark.psi.publish.base.bom.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class BomApproveTask extends SimpleTask {

	private GUID bomId;
	
	private boolean approved ;

	public GUID getBomId() {
		return bomId;
	}

	public BomApproveTask(GUID bomId,boolean approved) {
		this.bomId = bomId;this.approved = approved;
	}

	public boolean isApproved() {
		return approved;
	}
}
