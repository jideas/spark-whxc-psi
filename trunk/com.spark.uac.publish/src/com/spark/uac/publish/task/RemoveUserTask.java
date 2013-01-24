package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 在认证中心删除指定用户
 * 
 */
public class RemoveUserTask extends SimpleTask {

	private GUID userId;

	public RemoveUserTask(GUID userId) {
		super();
		this.userId = userId;
	}

	public GUID getUserId() {
		return userId;
	}

}
