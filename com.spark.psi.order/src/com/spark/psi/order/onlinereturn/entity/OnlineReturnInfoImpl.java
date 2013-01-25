package com.spark.psi.order.onlinereturn.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.constant.OnlineReturnStatus;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnDet;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnInfo;

public class OnlineReturnInfoImpl implements OnlineReturnInfo {
	private GUID RECID;
	private String billsNo;
	private GUID memberId;
	private String memberName;
	private GUID onlineBillsId;
	private String onlineBillsNo;
	private GUID stationId;
	private String stationName;
	private double amount;
	private OnlineReturnStatus status;
	private String returnReason;
	private String stopReason;
	private String rejectReason;
	private GUID creatorId;
	private String creator;
	private long createDate;
	private GUID approvorId;
	private String approvor;
	private long approveDate;
	
	private GUID finishPerson;
	private String finishPersonName;
	private long finishedDate;
	private String memberPhone;
	private long onlineCreateDate;
	private double salesAmount;
	private int vantages;

	
	private List<OnlineReturnDet> items;

	public GUID getFinishPerson() {
		return finishPerson;
	}

	public void setFinishPerson(GUID finishPerson) {
		this.finishPerson = finishPerson;
	}

	public String getFinishPersonName() {
		return finishPersonName;
	}

	public void setFinishPersonName(String finishPersonName) {
		this.finishPersonName = finishPersonName;
	}

	public long getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(long finishedDate) {
		this.finishedDate = finishedDate;
	}

	public GUID getRECID() {
		return RECID;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
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

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
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

	public OnlineReturnStatus getStatus() {
		return status;
	}

	public void setStatus(OnlineReturnStatus status) {
		this.status = status;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public GUID getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public GUID getApprovorId() {
		return approvorId;
	}

	public void setApprovorId(GUID approvorId) {
		this.approvorId = approvorId;
	}

	public String getApprovor() {
		return approvor;
	}

	public void setApprovor(String approvor) {
		this.approvor = approvor;
	}

	public long getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(long approveDate) {
		this.approveDate = approveDate;
	}

	public List<OnlineReturnDet> getItems() {
		return items;
	}

	public void setItems(List<OnlineReturnDet> items) {
		this.items = items;
	}

	public void setVantages(int vantages) {
		this.vantages = vantages;
	}

	public int getVantages() {
		return vantages;
	}

}
