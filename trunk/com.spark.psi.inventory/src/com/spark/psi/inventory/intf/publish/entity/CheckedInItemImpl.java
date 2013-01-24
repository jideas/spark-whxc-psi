package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.sheet.entity.CheckedInItem;

public class CheckedInItemImpl implements CheckedInItem {

	private GUID checkedInPerson;
	private String checkedInPersonName;
	private long checkinDate;
	private long createDate;
	private String relaBillsNo;
	private GUID relaBillsId;
	private GUID sheetId;
	private String sheetNo;
	private CheckingInStatus status;
	private GUID storeId;
	private String StoreName;
	private CheckingInType type;
	private double amount,askedAmount;
	public GUID getRelaBillsId() {
		return relaBillsId;
	}
	public void setRelaBillsId(GUID relaBillsId) {
		this.relaBillsId = relaBillsId;
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
	public GUID getCheckedInPerson() {
		return checkedInPerson;
	}
	public void setCheckedInPerson(GUID checkedInPerson) {
		this.checkedInPerson = checkedInPerson;
	}
	public String getCheckedInPersonName() {
		return checkedInPersonName;
	}
	public void setCheckedInPersonName(String checkedInPersonName) {
		this.checkedInPersonName = checkedInPersonName;
	}
	public long getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(long checkinDate) {
		this.checkinDate = checkinDate;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public String getRelaBillsNo() {
		return relaBillsNo;
	}
	public void setRelaBillsNo(String relaBillsNo) {
		this.relaBillsNo = relaBillsNo;
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
	public CheckingInStatus getStatus() {
		return status;
	}
	public void setStatus(CheckingInStatus status) {
		this.status = status;
	}
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return StoreName;
	}
	public void setStoreName(String storeName) {
		StoreName = storeName;
	}
	public CheckingInType getType() {
		return type;
	}
	public void setType(CheckingInType type) {
		this.type = type;
	}
 

}
