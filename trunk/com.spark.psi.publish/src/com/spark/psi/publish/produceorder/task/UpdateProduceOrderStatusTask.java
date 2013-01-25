package com.spark.psi.publish.produceorder.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

public class UpdateProduceOrderStatusTask extends Task<UpdateProduceOrderStatusTask.Method> {

	public enum Method
	{
		/**
		 * 提交
		 */
		Submit,
		/**
		 * 批准
		 */
		Approve,
		/**
		 * 退回
		 */
		Deny,
		/**
		 * 完成
		 */
		Finish;
	}

	private GUID id;
	private String rejectReason;
	public GUID getId() {
		return id;
	}
	public UpdateProduceOrderStatusTask(GUID id) {

		this.id = id;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	
}
