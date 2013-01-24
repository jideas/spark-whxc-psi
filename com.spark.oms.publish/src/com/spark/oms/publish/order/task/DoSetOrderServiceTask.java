package com.spark.oms.publish.order.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * 1、启动新订单的服务为正式服务
 * 2、申请启用服务、中断服务。
 */
public class DoSetOrderServiceTask extends Task<DoSetOrderServiceTask.Method>{
	public enum Method{
		//启用服务
		EnableRun,
		//确认终止
		ConfirmSuspend,
		//申请停用
		ApplaySuspend,
		//取消中断
		CancleSuspend;
	};
	private GUID serviceId;
	
	public GUID getServiceId() {
		return serviceId;
	}

	public void setServiceId(GUID serviceId) {
		this.serviceId = serviceId;
	}
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
