package com.spark.onlineorder.intf.entity;

import com.jiuqi.dna.core.type.GUID;

public class OnlineOrderLogEntity {

	private GUID id;//	�б�ʶ
	private GUID billsId;//	����GUID
	private long processingTime;//	����ʱ��
	private String message;//	������Ϣ
	private String operator;//	������
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getBillsId() {
		return billsId;
	}
	public void setBillsId(GUID billsId) {
		this.billsId = billsId;
	}
	public long getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(long processingTime) {
		this.processingTime = processingTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}

}
