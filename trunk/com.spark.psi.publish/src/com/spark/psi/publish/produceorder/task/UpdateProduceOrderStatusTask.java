package com.spark.psi.publish.produceorder.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

public class UpdateProduceOrderStatusTask extends Task<UpdateProduceOrderStatusTask.Method> {

	public enum Method
	{
		/**
		 * �ύ
		 */
		Submit,
		/**
		 * ��׼
		 */
		Approve,
		/**
		 * �˻�
		 */
		Deny,
		/**
		 * ���
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
