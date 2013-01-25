package com.spark.psi.order.onlinereturn.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.constant.OnlineReturnStatus;

public class ChangeStatusOnlineReturnTask extends SimpleTask {

	public ChangeStatusOnlineReturnTask(GUID id, OnlineReturnStatus status) {
		this.id = id;
		this.status = status;
	}

	public ChangeStatusOnlineReturnTask(GUID id, OnlineReturnStatus status, String reason) {
		this.id = id;
		this.status = status;
		this.reason = reason;
	}

	private GUID id;
	private OnlineReturnStatus status;
	private String reason;
	private GUID relaBillsId;

	public GUID getId() {
		return id;
	}

	public GUID getRelaBillsId() {
		return relaBillsId;
	}

	public void setRelaBillsId(GUID relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	public OnlineReturnStatus getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}
}
