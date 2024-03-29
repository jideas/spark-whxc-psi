package com.spark.psi.order.onlinereturn.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.onlinereturn.task.OnlineReturnInfoTaskItem;

public class UpdateOnlineReturnTask extends SimpleTask {
	private GUID RECID;
	private GUID memberId;
	private String memberName;
	private GUID onlineBillsId;
	private String onlineBillsNo;
	private GUID stationId;
	private String stationName;
	private double amount;
	private String returnReason;
	private List<OnlineReturnInfoTaskItem> items;

	private String memberPhone;
	private long onlineCreateDate;
	private double salesAmount;
	private int vantages;

	public GUID getRECID() {
		return RECID;
	} 

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public GUID getMemberId() {
		return memberId;
	}

	public void setMemberId(GUID memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public long getOnlineCreateDate() {
		return onlineCreateDate;
	}

	public void setOnlineCreateDate(long onlineCreateDate) {
		this.onlineCreateDate = onlineCreateDate;
	}

	public double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public GUID getOnlineBillsId() {
		return onlineBillsId;
	}

	public void setOnlineBillsId(GUID onlineBillsId) {
		this.onlineBillsId = onlineBillsId;
	}

	public String getOnlineBillsNo() {
		return onlineBillsNo;
	}

	public void setOnlineBillsNo(String onlineBillsNo) {
		this.onlineBillsNo = onlineBillsNo;
	}

	public GUID getStationId() {
		return stationId;
	}

	public void setStationId(GUID stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public List<OnlineReturnInfoTaskItem> getItems() {
		return items;
	}

	public void setItems(List<OnlineReturnInfoTaskItem> items) {
		this.items = items;
	}

	public void setVantages(int vantages) {
		this.vantages = vantages;
	}

	public int getVantages() {
		return vantages;
	}
}
