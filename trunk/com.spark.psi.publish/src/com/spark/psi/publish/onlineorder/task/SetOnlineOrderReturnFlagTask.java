package com.spark.psi.publish.onlineorder.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * ���϶����˻���Ч�󣬻�д�˻���ʶ
 *
 */
public class SetOnlineOrderReturnFlagTask extends SimpleTask {

	private GUID id;

	public SetOnlineOrderReturnFlagTask(GUID id) {
		super();
		this.id = id;
	}

	public GUID getId() {
		return id;
	}
	
}
