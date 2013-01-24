package com.spark.psi.inventory.intf.entity.outstorage.mod;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * 确认出库
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author Wangtiancai
 * @version 2012-3-1
 */

public class SureOutstorage {

	private GUID RECID;
	private String sheetNo;
	private String checkoutType;
	private GUID partnerId;
	private String partnerName;
	private String namePY;
	private String partnerShortName;
	private GUID relaBillsId;
	private String relaBillsNo;
	private GUID storeId;
	private String storeName;
	private String storeNamePY;
	private String goodsFrom;
	private String goodsUse;
	private String takePerson;
	private String takeUnit;
	private String vouchersNo;
	private String remark;
	private double amount;
	private double receiptedAmount;
	private String receiptedFlag;
	private long checkoutDate;
	private GUID checkoutPerson;
	private String checkoutPersonName;
	private GUID deptId;
	private boolean isReceipting;
	private GUID creatorId;
	private String creator;

	private List<SureOutItem> items;

	public List<SureOutItem> getItems() {
		return items;
	}

	public void setItems(List<SureOutItem> items) {
		this.items = items;
	}

	public GUID getRECID() {
		return RECID;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	public String getCheckoutType() {
		return checkoutType;
	}

	public void setCheckoutType(String checkoutType) {
		this.checkoutType = checkoutType;
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

	public String getTakePerson() {
		return takePerson;
	}

	public void setTakePerson(String takePerson) {
		this.takePerson = takePerson;
	}

	public String getTakeUnit() {
		return takeUnit;
	}

	public void setTakeUnit(String takeUnit) {
		this.takeUnit = takeUnit;
	}

	public String getVouchersNo() {
		return vouchersNo;
	}

	public void setVouchersNo(String vouchersNo) {
		this.vouchersNo = vouchersNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getReceiptedAmount() {
		return receiptedAmount;
	}

	public void setReceiptedAmount(double receiptedAmount) {
		this.receiptedAmount = receiptedAmount;
	}

	public String getReceiptedFlag() {
		return receiptedFlag;
	}

	public void setReceiptedFlag(String receiptedFlag) {
		this.receiptedFlag = receiptedFlag;
	}

	public long getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(long checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public GUID getCheckoutPerson() {
		return checkoutPerson;
	}

	public void setCheckoutPerson(GUID checkoutPerson) {
		this.checkoutPerson = checkoutPerson;
	}

	public String getCheckoutPersonName() {
		return checkoutPersonName;
	}

	public void setCheckoutPersonName(String checkoutPersonName) {
		this.checkoutPersonName = checkoutPersonName;
	}

	public GUID getDeptId() {
		return deptId;
	}

	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
	}

	public boolean isReceipting() {
		return isReceipting;
	}

	public void setReceipting(boolean isReceipting) {
		this.isReceipting = isReceipting;
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
