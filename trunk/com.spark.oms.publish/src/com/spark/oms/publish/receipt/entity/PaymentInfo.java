package com.spark.oms.publish.receipt.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 收支记录
 */
public class PaymentInfo {
	//id
	private GUID id;
	//项目名称
	private String name;
	//项目类型
	private String type;
	//收入
	private double income;
	//支出
	private double outlay;
	//余额总计
	private double totalAmount;
	//记录日期
	private long recordDate;
	//费用来源
	private String sourceType;
	//收费服务
	private GUID   orderServiceId;
	public GUID getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public double getIncome() {
		return income;
	}
	public double getOutlay() {
		return outlay;
	}
	public String getSourceType() {
		return sourceType;
	}
	public GUID getOrderServiceId() {
		return orderServiceId;
	}
	
	public long getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(long recordDate) {
		this.recordDate = recordDate;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public void setOutlay(double outlay) {
		this.outlay = outlay;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public void setOrderServiceId(GUID orderServiceId) {
		this.orderServiceId = orderServiceId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
