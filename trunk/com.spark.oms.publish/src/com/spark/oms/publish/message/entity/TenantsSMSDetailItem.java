package com.spark.oms.publish.message.entity;

import java.util.ArrayList;
import java.util.List;


public class TenantsSMSDetailItem {
	/**
	 * 租户姓名
	 */
	private String tenantsName;
	/**
	 * 发送成功
	 */
	private int succeedNum;
	/**
	 * 发送失败
	 */
	private int failedNum;
	
	/**
	 * 发送失败
	 */
	private double amount;
	
	private List<MessageInfo> list = new ArrayList<MessageInfo>();
	
	public void add(MessageInfo msg){
		list.add(msg);
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

	public int getSucceedNum() {
		return succeedNum;
	}

	public void setSucceedNum(int succeedNum) {
		this.succeedNum = succeedNum;
	}

	public int getFailedNum() {
		return failedNum;
	}

	public void setFailedNum(int failedNum) {
		this.failedNum = failedNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<MessageInfo> getList() {
		return list;
	}

	public void setList(List<MessageInfo> list) {
		this.list = list;
	}
	

}
