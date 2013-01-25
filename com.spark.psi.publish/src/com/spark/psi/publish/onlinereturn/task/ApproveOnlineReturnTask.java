package com.spark.psi.publish.onlinereturn.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class ApproveOnlineReturnTask extends SimpleTask {

	/**
	 * @param approval
	 *            是否批准
	 * @param reason
	 *            驳回时的驳回原因
	 */
	public ApproveOnlineReturnTask(GUID id, boolean approval, String reason,GUID onlineOrderId) {
		this.approval = approval;
		this.reason = reason;
		this.id = id;
		this.onlineOrderId = onlineOrderId;
	}

	private boolean approval;
	private GUID id;
	private String reason;
	private GUID onlineOrderId;
	
	public boolean isApproval() {
		return approval;
	}

	public String getReason() {
		return reason;
	}
	
	public GUID getId(){
		return id;
	}

	public GUID getOnlineOrderId() {
		return onlineOrderId;
	} 
}
