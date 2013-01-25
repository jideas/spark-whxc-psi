package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

public class UpdatePaymentStatusTask extends Task<UpdatePaymentStatusTask.Method> {

	public enum Method
	{
		Submit,
		Approve,
		Deny;
	}
	
	private GUID[] ids;

	public UpdatePaymentStatusTask(GUID... ids) {
		super();
		this.ids = ids;
	}

	public GUID[] getIds() {
		return ids;
	}
	
	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}

	public String getDenyReason() {
		return denyReason;
	}

	private String denyReason;
	
}
