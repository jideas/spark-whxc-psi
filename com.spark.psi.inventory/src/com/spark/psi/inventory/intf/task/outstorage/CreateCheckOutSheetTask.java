package com.spark.psi.inventory.intf.task.outstorage;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

public class CreateCheckOutSheetTask extends SimpleTask {

	private GUID RECID;// �б�ʶ
	private String sheetNo;// ���ⵥ��
	private String checkoutType;// ��������
	private GUID partnerId;// ��Ӧ��/�ͻ�recid
	private String partnerName;// ��Ӧ��/�ͻ�����
	private String namePY;// ƴ��
	private String partnerShortName;// ���
	private GUID relaBillsId;// ��ص���recid
	private String relaBillsNo;// ��ص��ݱ��
	private GUID storeId;// �ֿ�recid
	private String storeName;// �ֿ�����
	private String storeNamePY;// ƴ��
	private String goodsFrom;// ��Ʒ��Դ
	private String goodsUse;// ��Ʒ��;
	private String takePerson;// �����
	private String takeUnit;// �����λ
	private String vouchersNo;// ƾ֤��
	private String remark;// ��ע
	private double amount;// ������
	private double receiptedAmount;// ���տ���
	private String receiptedFlag;// �տ�״̬
	private boolean isReceipting;// �Ƿ�����տ

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
