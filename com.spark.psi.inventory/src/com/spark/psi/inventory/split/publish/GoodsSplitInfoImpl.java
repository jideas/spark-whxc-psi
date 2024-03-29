package com.spark.psi.inventory.split.publish;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Goods;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Material;
import com.spark.psi.publish.split.entity.GoodsSplitInfo;

public class GoodsSplitInfoImpl implements GoodsSplitInfo {
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

	private List<GoodsSplitDet_Goods> goodsDets;
	private List<GoodsSplitDet_Material> materialDets;

	public List<GoodsSplitDet_Goods> getGoodsDets() {
		return goodsDets;
	}

	public List<GoodsSplitDet_Material> getMaterialDets() {
		return materialDets;
	}

	public void setGoodsDets(List<GoodsSplitDet_Goods> goodsDets) {
		this.goodsDets = goodsDets;
	}

	public void setMaterialDets(List<GoodsSplitDet_Material> materialDets) {
		this.materialDets = materialDets;
	}

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

	public GoodsSplitStatus getStatus() {
		return GoodsSplitStatus.getStatus(status);
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
