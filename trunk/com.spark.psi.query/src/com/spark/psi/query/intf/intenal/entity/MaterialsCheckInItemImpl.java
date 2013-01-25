package com.spark.psi.query.intf.intenal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckInItem;

/**
 * ������ⵥ��ѯʵ��<BR>
 * ��ѯ������<BR>
 * MaterialsCheckInListEntity+GetMaterialsCheckInListKey;
 *
 */
public class MaterialsCheckInItemImpl implements MaterialsCheckInItem {

	private GUID sheetId;//	��ⵥrecid
	private String sheetNo;//��ⵥ��
	private GUID goodsId;//	��Ʒrecid
	private String goodsCode;//	��Ʒ����
	private String goodsName;//	��Ʒ����
	private String unit;//	��Ʒ��λ
	private double price;//	��Ʒ����
	private double amount;//	��Ʒ���
	private double count;//	�������
	private double standardCost;//�ο��ɱ�
	private double standardAmount;//�ο����
	private GUID purchaseSheetId;
	private String purchaseSheetNo;
	private long checkinDate;
	private CheckingInType checkingInType;
	private GUID partnerId;
	private String partnerName;
	private String partnerNo;
	private String department;
	
	public long getCheckinDate() {
		return checkinDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setCheckinDate(long checkinDate) {
		this.checkinDate = checkinDate;
	}
	public CheckingInType getCheckingInType() {
		return checkingInType;
	}
	public void setCheckingInType(CheckingInType checkingInType) {
		this.checkingInType = checkingInType;
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
	public GUID getPurchaseSheetId() {
		return purchaseSheetId;
	}
	public void setPurchaseSheetId(GUID purchaseSheetId) {
		this.purchaseSheetId = purchaseSheetId;
	}
	public String getPurchaseSheetNo() {
		return purchaseSheetNo;
	}
	public void setPurchaseSheetNo(String purchaseSheetNo) {
		this.purchaseSheetNo = purchaseSheetNo;
	}
	public GUID getSheetId() {
		return sheetId;
	}
	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public GUID getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getStandardCost() {
		return standardCost;
	}
	public void setStandardCost(double standardCost) {
		this.standardCost = standardCost;
	}
	public double getStandardAmount() {
		return standardAmount;
	}
	public void setStandardAmount(double standardAmount) {
		this.standardAmount = standardAmount;
	}
	public void setPartnerNo(String partnerNo) {
		this.partnerNo = partnerNo;
	}
	public String getPartnerNo() {
		return partnerNo;
	}
	
	

	
}
