package com.spark.oms.publish.message.entity;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;

public class MessageInfo {
	private GUID id;
	//�⻧id
	private GUID tenantsId;
	//�⻧����
	private String tenantsName;
	//�����ײ�Id
	private GUID serviceId;
	//�����ײ�����
	private String serviceName;
	//�ƶ��绰���绰����;�绰����
	private String mobile;
	//��������
	private String content;
	//���յ�ʱ��
	private long receiveTime;
	//���͵�ʱ��
	private long SentTime;
	//����״̬���ѷ���|δ����
	private String status;
	//�ɹ�����
	private int succeedNum;
	//ʧ������
	private int failedNum;
	//����ǰ�˻����
	private double beforeSendAccountBalance;
	//���ͺ��˻����
	private double AfterSendAccountBalance;
	//�ɱ�
	private double cost;
	//�շ�
	private double charge;
	public GUID getId() {
		return id;
	}
	public GUID getTenantsId() {
		return tenantsId;
	}
	public String getTenantsName() {
		return tenantsName;
	}
	public String getMobile() {
		return mobile;
	}
	public String getContent() {
		return content;
	}
	public long getReceiveTime() {
		return receiveTime;
	}
	public long getSentTime() {
		return SentTime;
	}
	public String getStatus() {
		return status;
	}
	public int getSucceedNum() {
		return succeedNum;
	}
	public int getFailedNum() {
		return failedNum;
	}
	public double getBeforeSendAccountBalance() {
		return beforeSendAccountBalance;
	}
	public double getAfterSendAccountBalance() {
		return AfterSendAccountBalance;
	}
	public GUID getServiceId() {
		return serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public double getCost() {
		return cost;
	}
	public double getCharge() {
		return charge;
	}
	
	public void setId(GUID id) {
		this.id = id;
	}
	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}
	public void setServiceId(GUID serviceId) {
		this.serviceId = serviceId;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}
	public void setSentTime(long sentTime) {
		SentTime = sentTime;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setSucceedNum(int sucessNum) {
		this.succeedNum = sucessNum;
	}
	public void setFailedNum(int failNum) {
		this.failedNum = failNum;
	}
	public void setBeforeSendAccountBalance(double beforeSendAccountBalance) {
		this.beforeSendAccountBalance = beforeSendAccountBalance;
	}
	public void setAfterSendAccountBalance(double afterSendAccountBalance) {
		AfterSendAccountBalance = afterSendAccountBalance;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	
}
