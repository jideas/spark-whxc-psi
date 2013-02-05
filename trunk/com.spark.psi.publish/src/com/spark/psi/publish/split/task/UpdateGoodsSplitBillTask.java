package com.spark.psi.publish.split.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;

public class UpdateGoodsSplitBillTask extends SimpleTask {
	private GUID RECID;
	private String rejectReason;
	private String remark;
	private GUID storeId;
	private GoodsSplitStatus status;

	private List<GoodsSplitTaskDet> goodsDets;
	private List<GoodsSplitTaskDet> materialDets;

	public GUID getRECID() {
		return RECID;
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

	public GoodsSplitStatus getStatus() {
		return status;
	}

	public void setStatus(GoodsSplitStatus status) {
		this.status = status;
	}

	public List<GoodsSplitTaskDet> getGoodsDets() {
		return goodsDets;
	}

	public List<GoodsSplitTaskDet> getMaterialDets() {
		return materialDets;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
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

	public void setGoodsDets(List<GoodsSplitTaskDet> goodsDets) {
		this.goodsDets = goodsDets;
	}

	public void setMaterialDets(List<GoodsSplitTaskDet> materialDets) {
		this.materialDets = materialDets;
	}
 
}
