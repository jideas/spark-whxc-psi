package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.constant.OnlineReturnStatus;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnItem;

public class OnlineReturnItemImpl implements OnlineReturnItem {

	private GUID id;
	private String billsNo, onlineBillsNo, customer, creator,finishPerson,stationName;
	private double amount;
	private long createDate,finishedDate;
	private OnlineReturnStatus status;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}

	public String getOnlineBillsNo() {
		return onlineBillsNo;
	}

	public void setOnlineBillsNo(String onlineBillsNo) {
		this.onlineBillsNo = onlineBillsNo;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public OnlineReturnStatus getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = OnlineReturnStatus.getStatus(status);
	}

	public String getFinishPerson() {
		return finishPerson;
	}

	public void setFinishPerson(String finishPerson) {
		this.finishPerson = finishPerson;
	}

	public long getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(long finishedDate) {
		this.finishedDate = finishedDate;
	}

}
