package com.spark.psi.inventory.intf.task.outstorage;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

public class CreateCheckOutSheetTask extends SimpleTask {

	private GUID RECID;// 行标识
	private String sheetNo;// 出库单号
	private String checkoutType;// 出库类型
	private GUID partnerId;// 供应商/客户recid
	private String partnerName;// 供应商/客户名称
	private String namePY;// 拼音
	private String partnerShortName;// 简称
	private GUID relaBillsId;// 相关单据recid
	private String relaBillsNo;// 相关单据编号
	private GUID storeId;// 仓库recid
	private String storeName;// 仓库名称
	private String storeNamePY;// 拼音
	private String goodsFrom;// 物品来源
	private String goodsUse;// 物品用途
	private String takePerson;// 提货人
	private String takeUnit;// 提货单位
	private String vouchersNo;// 凭证号
	private String remark;// 备注
	private double amount;// 出库金额
	private double receiptedAmount;// 已收款金额
	private String receiptedFlag;// 收款状态
	private boolean isReceipting;// 是否关联收款单

	private List<CreateCheckOutSheetTaskItem> items;
	private boolean success;

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

	public boolean isReceipting() {
		return isReceipting;
	}

	public void setReceipting(boolean isReceipting) {
		this.isReceipting = isReceipting;
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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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

	public List<CreateCheckOutSheetTaskItem> getItems() {
		return items;
	}

	public void setItems(List<CreateCheckOutSheetTaskItem> items) {
		this.items = items;
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

	public boolean IsReceipting() {
		return isReceipting;
	}

	public void setIsReceipting(boolean isReceipting) {
		this.isReceipting = isReceipting;
	}
}
