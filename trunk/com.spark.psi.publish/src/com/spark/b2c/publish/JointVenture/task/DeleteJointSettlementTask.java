package com.spark.b2c.publish.JointVenture.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * ɾ������
 *
 */
public class DeleteJointSettlementTask extends SimpleTask {

	private GUID[] ids;

	public DeleteJointSettlementTask(GUID... ids) {
		super();
		this.ids = ids;
	}

	public GUID[] getIds() {
		return ids;
	}
	
}
