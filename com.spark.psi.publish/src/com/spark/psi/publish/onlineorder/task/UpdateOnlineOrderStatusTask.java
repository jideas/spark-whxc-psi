package com.spark.psi.publish.onlineorder.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
/**
 * �޸����϶���״̬
 *
 */
public class UpdateOnlineOrderStatusTask extends Task<UpdateOnlineOrderStatusTask.Method> {
	public enum Method{
		/**
		 * ���
		 */
		Picking,
		/**
		 * ����
		 */
		Deliver,
		/**
		 * ����
		 */
		Arrival,
		/**
		 * ���
		 */
		Finish
		
	}
	
	private GUID[] ids;

	public GUID[] getIds() {
		return ids;
	}

	/**
	 * 
	 * @param ids ���϶���ID����
	 */
	public UpdateOnlineOrderStatusTask(GUID... ids) {
		super();
		this.ids = ids;
	}
	
	public void setNoVerificationReason(String noVerificationReason) {
		this.noVerificationReason = noVerificationReason;
	}

	public String getNoVerificationReason() {
		return noVerificationReason;
	}

	private String noVerificationReason; 

}
