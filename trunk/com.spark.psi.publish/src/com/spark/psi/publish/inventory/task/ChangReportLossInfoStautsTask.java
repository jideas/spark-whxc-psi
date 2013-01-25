package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ReportLossStatus;

public class ChangReportLossInfoStautsTask extends SimpleTask {
	
	public static enum Operation {
		submit, approval, reject
	}
	
	private GUID sheetId;
	
	private long changeTime;
	private GUID changePersonId;
	private String changePersonName;
	private String rejectReason;
	
	private ReportLossStatus status;
	
	private ReportLossInfoTask.Item[] items;
	
	private Operation operation;
	
	public ChangReportLossInfoStautsTask(GUID sheetId, ReportLossStatus status) {
		this.sheetId = sheetId;
		this.status = status;
	}
	
	
	
	public Operation getOperation() {
		return operation;
	}



	public void setOperation(Operation operation) {
		this.operation = operation;
	}



	public GUID getSheetId() {
		return this.sheetId;
	}
	
	public ReportLossStatus getStatus() {
		return this.status;
	}

	public long getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(long changeTime) {
		this.changeTime = changeTime;
	}

	public GUID getChangePersonId() {
		return changePersonId;
	}

	public void setChangePersonId(GUID changePersonId) {
		this.changePersonId = changePersonId;
	}

	public String getChangePersonName() {
		return changePersonName;
	}

	public void setChangePersonName(String changePersonName) {
		this.changePersonName = changePersonName;
	}

	public ReportLossInfoTask.Item[] getItems() {
		return items;
	}

	public void setItems(ReportLossInfoTask.Item[] items) {
		this.items = items;
	}



	public String getRejectReason() {
		return rejectReason;
	}



	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	
}
