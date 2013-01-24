package com.spark.psi.base.publicimpl;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;
import com.spark.psi.publish.base.bom.entity.BomInfo;
import com.spark.psi.publish.base.bom.entity.BomInfoItem;

public class BomInfoImpl implements BomInfo {

	private GUID id;
	private BOM_STATUS status;
	private GUID goodsItemId;
	private String goodsNo;
	private GUID creatorId;
	private String creator;
	private long createDate;
	private String bomNo;
	private List<BomInfoItem> bomInfoItems;
	private String approvePersonName;
	private GUID approvePerson;
	private long approveDate;
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getApproveDate() {
		return approveDate;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public GUID getApprovePerson() {
		return approvePerson;
	}

	public String getApprovePersonName() {
		return approvePersonName;
	}

	public List<BomInfoItem> getBomInfoItems() {
		return bomInfoItems;
	}

	public String getBomNo() {
		return bomNo;
	}

	public long getCreateDate() {

		return createDate;
	}

	public String getCreator() {

		return creator;
	}

	public GUID getCreatorId() {

		return creatorId;
	}

	public GUID getGoodsItemId() {

		return goodsItemId;
	}

	public GUID getId() {

		return id;
	}

	public BOM_STATUS getStatus() {

		return status;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public void setStatus(BOM_STATUS status) {
		this.status = status;
	}

	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public void setBomNo(String bomNo) {
		this.bomNo = bomNo;
	}

	public void setBomInfoItems(List<BomInfoItem> bomInfoItems) {
		this.bomInfoItems = bomInfoItems;
	}

	public void setApprovePersonName(String approvePersonName) {
		this.approvePersonName = approvePersonName;
	}

	public void setApprovePerson(GUID approvePerson) {
		this.approvePerson = approvePerson;
	}

	public void setApproveDate(long approveDate) {
		this.approveDate = approveDate;
	}

}
