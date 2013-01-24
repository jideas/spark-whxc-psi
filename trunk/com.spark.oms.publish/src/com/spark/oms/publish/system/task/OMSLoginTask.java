package com.spark.oms.publish.system.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class OMSLoginTask extends SimpleTask {

	private GUID userId;
	private String userCode;

	public GUID getUserId() {
		return userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public OMSLoginTask(GUID userId, String userCode) {
		super();
		this.userId = userId;
		this.userCode = userCode;
	}
}