package com.spark.b2c.publish.JointVenture.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * ½áËãµ¥²Ù×÷
 *
 */
public class UpdateJointSettlementStatusTask extends Task<UpdateJointSettlementStatusTask.Method> {

	public enum Method
	{
		Submit,
		Approve,
		Deny;
	}
	
	private GUID id;

	public UpdateJointSettlementStatusTask(GUID id) {
		super();
		this.id = id;
	}

	public GUID getId() {
		return id;
	}
	
	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}

	public String getDenyReason() {
		return denyReason;
	}

	private String denyReason;
	
}
