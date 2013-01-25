package com.spark.psi.publish.onlineorder.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
/**
 * 修改网上订单状态
 *
 */
public class UpdateOnlineOrderStatusTask extends Task<UpdateOnlineOrderStatusTask.Method> {
	public enum Method{
		/**
		 * 拣货
		 */
		Picking,
		/**
		 * 配送
		 */
		Deliver,
		/**
		 * 到货
		 */
		Arrival,
		/**
		 * 完成
		 */
		Finish
		
	}
	
	private GUID[] ids;

	public GUID[] getIds() {
		return ids;
	}

	/**
	 * 
	 * @param ids 网上订单ID数组
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
