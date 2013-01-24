package com.spark.psi.inventory.intf.publish.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;

public class CheckInBaseInfoImpl implements CheckInBaseInfo {  
	private GUID id;
	private String sheetNo;
	private CheckingInType sheetType;
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
	private String remark;
	private String buyPerson;
	private long buyDate;
	private double amount;
	private double askedAmount;
	private double paidAmount;
	private double disAmount;
	private long checkinDate;
	private GUID checkinPerson;
	private String checkinPersonName;
	private GUID deptId;
	private GUID creatorId;
	private String creator; 
	
	private List<CheckInBaseInfoItem> items;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public CheckingInType getSheetType() {
		return sheetType;
	}
	public void setSheetType(CheckingInType sheetType) {
		this.sheetType = sheetType;
	}
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public List<CheckInBaseInfoItem> getItems() {
		return items;
	}
	public void setItems(List<CheckInBaseInfoItem> items) {
		this.items = items;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBuyPerson() {
		return buyPerson;
	}
	public void setBuyPerson(String buyPerson) {
		this.buyPerson = buyPerson;
	}
	public long getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(long buyDate) {
		this.buyDate = buyDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAskedAmount() {
		return askedAmount;
	}
	public void setAskedAmount(double askedAmount) {
		this.askedAmount = askedAmount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(double disAmount) {
		this.disAmount = disAmount;
	}
	public long getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(long checkinDate) {
		this.checkinDate = checkinDate;
	}
	public GUID getCheckinPerson() {
		return checkinPerson;
	}
	public void setCheckinPerson(GUID checkinPerson) {
		this.checkinPerson = checkinPerson;
	}
	public String getCheckinPersonName() {
		return checkinPersonName;
	}
	public void setCheckinPersonName(String checkinPersonName) {
		this.checkinPersonName = checkinPersonName;
	}
	public GUID getDeptId() {
		return deptId;
	}
	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
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
