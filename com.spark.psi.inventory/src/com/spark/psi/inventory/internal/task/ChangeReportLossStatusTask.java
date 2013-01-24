package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class ChangeReportLossStatusTask extends SimpleTask {
	private GUID id;
	private String status;
	private String nextStatus;
	
	private long applyDate;
	
	private long approvalDate;
	private GUID approvalPersonId;
	private String approvalPersonName;
	private String rejectReason;
	
	private boolean isSuccess = false;
	
	public ChangeReportLossStatusTask(GUID id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNextStatus() {
		return nextStatus;
	}

	public void setNextStatus(String nextStatus) {
		this.nextStatus = nextStatus;
	}

	public GUID getId() {
		return id;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public long getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(long applyDate) {
		this.applyDate = applyDate;
	}

	public long getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(long approvalDate) {
		this.approvalDate = approvalDate;
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

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

}
