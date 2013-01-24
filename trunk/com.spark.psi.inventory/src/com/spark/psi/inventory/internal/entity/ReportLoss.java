package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

public class ReportLoss {
	private GUID id;
	private GUID storeId;
	private String storeName;
	private String sheetNo;
	private long applyDate;
	private String remark;
	private GUID creatorId;
	private String creator;
	private long createDate;
	private GUID approvalPersonId;
	private String approvalPersonName;
	private long approvalDate;
	private String rejectReason;
	private String status;
	private String reportType;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public long getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(long applyDate) {
		this.applyDate = applyDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public GUID getApprovalPersonId() {
		return approvalPersonId;
	}
	public void setApprovalPersonId(GUID approvalPersonId) {
		this.approvalPersonId = approvalPersonId;
	}
	public String getApprovalPersonName() {
		return approvalPersonName;
	}
	public void setApprovalPersonName(String approvalPersonName) {
		this.approvalPersonName = approvalPersonName;
	}
	public long getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(long approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	
}
