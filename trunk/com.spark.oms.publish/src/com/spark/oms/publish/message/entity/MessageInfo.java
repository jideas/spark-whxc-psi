package com.spark.oms.publish.message.entity;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;

public class MessageInfo {
	private GUID id;
	//租户id
	private GUID tenantsId;
	//租户名称
	private String tenantsName;
	//服务套餐Id
	private GUID serviceId;
	//服务套餐名称
	private String serviceName;
	//移动电话：电话号码;电话号码
	private String mobile;
	//发送内容
	private String content;
	//接收到时间
	private long receiveTime;
	//发送的时间
	private long SentTime;
	//发送状态：已发送|未发送
	private String status;
	//成功条数
	private int succeedNum;
	//失败条数
	private int failedNum;
	//发送前账户金额
	private double beforeSendAccountBalance;
	//发送后账户金额
	private double AfterSendAccountBalance;
	//成本
	private double cost;
	//收费
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
