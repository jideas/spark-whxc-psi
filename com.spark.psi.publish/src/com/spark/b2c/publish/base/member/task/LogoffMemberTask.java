package com.spark.b2c.publish.base.member.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class LogoffMemberTask extends SimpleTask{

	private GUID id;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}
}
