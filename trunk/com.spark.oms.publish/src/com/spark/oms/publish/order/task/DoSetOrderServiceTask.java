package com.spark.oms.publish.order.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * 1�������¶����ķ���Ϊ��ʽ����
 * 2���������÷����жϷ���
 */
public class DoSetOrderServiceTask extends Task<DoSetOrderServiceTask.Method>{
	public enum Method{
		//���÷���
		EnableRun,
		//ȷ����ֹ
		ConfirmSuspend,
		//����ͣ��
		ApplaySuspend,
		//ȡ���ж�
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
