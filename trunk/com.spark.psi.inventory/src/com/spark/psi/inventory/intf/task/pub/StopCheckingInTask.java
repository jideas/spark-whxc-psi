/**
 * 
 */
package com.spark.psi.inventory.intf.task.pub;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ÖÐÖ¹Èë¿âµ¥
 *
 */
public class StopCheckingInTask extends SimpleTask {

	private GUID relationOrderId;
	private String stopReason;
	
	public GUID getRelationOrderId() {
		return relationOrderId;
	}
	public void setRelationOrderId(GUID relationOrderId) {
		this.relationOrderId = relationOrderId;
	}
	public String getStopReason() {
		return stopReason;
	}
	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}
	
}
