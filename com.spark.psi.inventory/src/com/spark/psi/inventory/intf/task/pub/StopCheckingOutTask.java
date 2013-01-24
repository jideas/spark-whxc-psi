/**
 * 
 */
package com.spark.psi.inventory.intf.task.pub;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 中止出库需求
 *
 */
public class StopCheckingOutTask extends SimpleTask {
	
	private String stopReason;
	private GUID relationOrderId;
	
	public String getStopReason() {
		return stopReason;
	}
	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}
	public void setRelationOrderId(GUID relationOrderId) {
		this.relationOrderId = relationOrderId;
	}
	public GUID getRelationOrderId() {
		return relationOrderId;
	}
	
}
