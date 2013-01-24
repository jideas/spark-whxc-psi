package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ReportLossStatus;
import com.spark.psi.publish.inventory.entity.ReportLossInfo;

public class ReportLossInfoImpl implements ReportLossInfo {
	
	private GUID id;
	private String sheetNo;
	private GUID storeId;
	private String storeName;
	private long applyDate;
	private long createDate;
	private String createor;
	private GUID creatorId;
	private GUID approvalPersonId;
	private String approvalPersonName;
	private long approvalDate;
	private ReportLossInfoItemImpl[] items;
	private String remark;
	private ReportLossStatus status;
	private String rejectReason;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
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
	public long getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(long applyDate) {
		this.applyDate = applyDate;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public String getCreateor() {
		return createor;
	}
	public void setCreateor(String createor) {
		this.createor = createor;
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
	public ReportLossInfoItemImpl[] getItems() {
		return items;
	}
	public void setItems(ReportLossInfoItemImpl[] items) {
		this.items = items;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public ReportLossStatus getStatus() {
		return status;
	}
	public void setStatus(ReportLossStatus status) {
		this.status = status;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	
}
