package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;
import com.spark.psi.publish.base.bom.entity.BomItem;

public class BomItemImpl implements BomItem {

	private GUID id;
	private GUID goodsItemId;
	private String goodsName, goodsNo, goodsCode, goodsSpec, goodsUnit;
	private String bomNo;
	private GUID creatorId;
	private String creator;
	private long createDate;
	private GUID approvePerson;
	private String approvePersonName;
	private BOM_STATUS status;
	private String remark;

	public GUID getId() {
		return id;
	}

	public String getCreateDate() {
		return DateUtil.dateFromat(this.createDate);
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsSpec() {
		return goodsSpec;
	}

	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getBomNo() {
		return bomNo;
	}

	public void setBomNo(String bomNo) {
		this.bomNo = bomNo;
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

	public GUID getApprovePerson() {
		return approvePerson;
	}

	public void setApprovePerson(GUID approvePerson) {
		this.approvePerson = approvePerson;
	}

	public String getApprovePersonName() {
		return approvePersonName;
	}

	public void setApprovePersonName(String approvePersonName) {
		this.approvePersonName = approvePersonName;
	}

	public BOM_STATUS getStatus() {
		return status;
	}

	public void setStatus(BOM_STATUS status) {
		this.status = status;
	}
}
