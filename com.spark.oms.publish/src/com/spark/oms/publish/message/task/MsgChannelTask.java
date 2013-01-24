package com.spark.oms.publish.message.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class MsgChannelTask extends SimpleTask {

	private GUID id;
	/**
	 * ����
	 */
	private String name;
	/**
	 * ����
	 */
	private String code;
	/**
	 * �۸�
	 */
	private double price;
	/**
	 * ֧����������
	 */
	private String isBatchSend;
	/**
	 * ����
	 */
	private String remark;
	/**
	 * ֧������
	 */
	private String supportNetWork;
	/**
	 * �Ƿ�ɹ�
	 */
	private boolean success;
	/**
	 * ������Ϣ
	 */
	private String errors;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIsBatchSend() {
		return isBatchSend;
	}
	public void setIsBatchSend(String isBatchSend) {
		this.isBatchSend = isBatchSend;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSupportNetWork() {
		return supportNetWork;
	}
	public void setSupportNetWork(String supportNetWork) {
		this.supportNetWork = supportNetWork;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
}