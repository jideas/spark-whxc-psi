/**
 * 
 */
package com.spark.psi.inventory.intf.task.pub;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ����ִ����ⵥ
 *
 */
public class ExcuteCheckingInTask extends SimpleTask {

	private GUID relationOrderId;

	public void setRelationOrderId(GUID relationOrderId) {
		this.relationOrderId = relationOrderId;
	}

	public GUID getRelationOrderId() {
		return relationOrderId;
	}
}
