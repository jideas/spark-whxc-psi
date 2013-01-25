package com.spark.psi.publish.onlineorder.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteOnlineOrderTask extends SimpleTask {

	private GUID id;

	public GUID getId() {
		return id;
	}

	public DeleteOnlineOrderTask(GUID id) {
		super();
		this.id = id;
	}
	
}
