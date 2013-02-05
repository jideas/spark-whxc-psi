package com.spark.psi.inventory.split.publish;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.split.entity.GoodsSplitItem;

public class GoodsSplitItemImpl implements GoodsSplitItem {
	private GUID RECID;
	private String billNo;
	private String creator;
	private GUID creatorId;
	private long createDate;
	private String approvalPerson;
	private GUID approvalPersonId;
	private long approvalDate;
	private String distributPerson;
	private GUID distributPersonId;
	private long distributDate;
	private String status;
	private String rejectReason;
	private String remark;
	private GUID storeId;
	private long finishDate;
	public GUID getRECID() {
		return RECID;
	}
	public String getBillNo() {
		return billNo;
	}
	public String getCreator() {
		return creator;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public long getCreateDate() {
		return createDate;
	}
	public String getApprovalPerson() {
		return approvalPerson;
	}
	public GUID getApprovalPersonId() {
		return approvalPersonId;
	}
	public long getApprovalDate() {
		return approvalDate;
	}
	public String getDistributPerson() {
		return distributPerson;
	}
	public GUID getDistributPersonId() {
		return distributPersonId;
	}
	public long getDistributDate() {
		return distributDate;
	}
	public String getStatus() {
		return status;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public String getRemark() {
		return remark;
	}
	public GUID getStoreId() {
		return storeId;
	}
	public long getFinishDate() {
		return finishDate;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}
	public void setApprovalPersonId(GUID approvalPersonId) {
		this.approvalPersonId = approvalPersonId;
	}
	public void setApprovalDate(long approvalDate) {
		this.approvalDate = approvalDate;
	}
	public void setDistributPerson(String distributPerson) {
		this.distributPerson = distributPerson;
	}
	public void setDistributPersonId(GUID distributPersonId) {
		this.distributPersonId = distributPersonId;
	}
	public void setDistributDate(long distributDate) {
		this.distributDate = distributDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public void setFinishDate(long finishDate) {
		this.finishDate = finishDate;
	}
	
}
