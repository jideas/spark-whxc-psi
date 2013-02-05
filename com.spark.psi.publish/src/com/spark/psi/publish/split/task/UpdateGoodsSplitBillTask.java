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

	private List<UpdateGoodsSplitBillTaskDet> goodsDets;
	private List<UpdateGoodsSplitBillTaskDet> materialDets;

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

	public List<UpdateGoodsSplitBillTaskDet> getGoodsDets() {
		return goodsDets;
	}

	public List<UpdateGoodsSplitBillTaskDet> getMaterialDets() {
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

	public void setGoodsDets(List<UpdateGoodsSplitBillTaskDet> goodsDets) {
		this.goodsDets = goodsDets;
	}

	public void setMaterialDets(List<UpdateGoodsSplitBillTaskDet> materialDets) {
		this.materialDets = materialDets;
	}

	public class UpdateGoodsSplitBillTaskDet {
		private GUID id;
		private double count;
		private String reason;

		public GUID getId() {
			return id;
		}

		public double getCount() {
			return count;
		}

		public String getReason() {
			return reason;
		}

		public UpdateGoodsSplitBillTaskDet(GUID id, double count) {
			this.id = id;
			this.count = count;
		}
		public UpdateGoodsSplitBillTaskDet(GUID id, double count,String reason) {
			this.id = id;
			this.count = count;
			this.reason = reason;
		}
	}
}
