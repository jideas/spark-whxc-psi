package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 改变指定租户的指定用户的可用状态
 * 
 */
public class ChangeUserEnabledTask extends SimpleTask {

	private GUID[] userId;

	private boolean enabled;

	public ChangeUserEnabledTask(boolean enabled,GUID... userId) {
		super();
		this.userId = userId;
		this.enabled = enabled;
	}

	public GUID[] getUserId() {
		return userId;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
