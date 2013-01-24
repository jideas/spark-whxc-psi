package com.spark.oms.publish.receipt.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 新增收支记录
 * 
 * @author mengyongfeng
 *
 */
public class CreatePaymentTask extends SimpleTask {
	//id
	private GUID id;
	//项目名称
	private String name;
	//项目类型
	private String type;
	//收入存在默认值
	private double income = 0;
	//支出存在默认值
	private double outlay = 0;
	//记录日期
	private long recordDate;
	//收费服务
	private GUID   receiptId;
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
	
	public GUID getReceiptId() {
		return receiptId;
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
	
	public void setReceiptId(GUID receiptId) {
		this.receiptId = receiptId;
	}
}
