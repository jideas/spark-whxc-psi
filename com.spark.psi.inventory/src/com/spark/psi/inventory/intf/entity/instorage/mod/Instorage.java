package com.spark.psi.inventory.intf.entity.instorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * Èë¿â
 */

public class Instorage {

	private GUID RECID;
	private String sheetType;
	private GUID partnerId;
	private String partnerCode, deptName;
	private GUID creatorDeptId;
	private String partnerName;
	private String namePY;
	private String partnerShortName;
	private GUID relaBillsId;
	private String relaBillsNo;
	private GUID storeId;
	private boolean isStoped;
	private String stopReason;
	private String storeName;
	private String storeNamePY;
	private String remark;
	private double billsAmount;
	private double billsCount;
	private long checkinDate;
	private String checkinString;
	private String status;
	private long createDate;
	private GUID creatorId;
	private String creator;

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public GUID getCreatorDeptId() {
		return creatorDeptId;
	}

	public void setCreatorDeptId(GUID creatorDeptId) {
		this.creatorDeptId = creatorDeptId;
	}

	/**
	 * Other
	 */
	private String goodsFrom, goodsUse, purchasePerson;
	private long purchaseDate;

	public String getGoodsFrom() {
		return goodsFrom;
	}

	public void setGoodsFrom(String goodsFrom) {
		this.goodsFrom = goodsFrom;
	}

	public String getGoodsUse() {
		return goodsUse;
	}

	public void setGoodsUse(String goodsUse) {
		this.goodsUse = goodsUse;
	}

	public String getPurchasePerson() {
		return purchasePerson;
	}

	public void setPurchasePerson(String purchasePerson) {
		this.purchasePerson = purchasePerson;
	}

	public long getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(long purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public GUID getRECID() {
		return RECID;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public GUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getNamePY() {
		return namePY;
	}

	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}

	public String getPartnerShortName() {
		return partnerShortName;
	}

	public void setPartnerShortName(String partnerShortName) {
		this.partnerShortName = partnerShortName;
	}

	public GUID getRelaBillsId() {
		return relaBillsId;
	}

	public void setRelaBillsId(GUID relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	public String getRelaBillsNo() {
		return relaBillsNo;
	}

	public void setRelaBillsNo(String relaBillsNo) {
		this.relaBillsNo = relaBillsNo;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public boolean isStoped() {
		return isStoped;
	}

	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}

	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreNamePY() {
		return storeNamePY;
	}

	public void setStoreNamePY(String storeNamePY) {
		this.storeNamePY = storeNamePY;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getBillsAmount() {
		return billsAmount;
	}

	public void setBillsAmount(double billsAmount) {
		this.billsAmount = billsAmount;
	}

	public double getBillsCount() {
		return billsCount;
	}

	public void setBillsCount(double billsCount) {
		this.billsCount = billsCount;
	}

	public long getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(long checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getCheckinString() {
		return checkinString;
	}

	public void setCheckinString(String checkinString) {
		this.checkinString = checkinString;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
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

}
