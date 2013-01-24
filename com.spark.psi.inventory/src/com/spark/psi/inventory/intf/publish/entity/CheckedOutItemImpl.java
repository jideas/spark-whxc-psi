package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.inventory.sheet.entity.CheckedOutItem;

public class CheckedOutItemImpl implements CheckedOutItem {
	
	private String takerUnit,taker,vouchersNumber;
	private GUID checkedOutPerson;
	private String checkedOutPersonName;
	private long checkOutDate;
	private long createDate;
	private String relaBillsNo;
	private GUID sheetId;
	private String sheetNo;
	private CheckingInStatus status;
	private GUID storeId;
	private String StoreName;
	private CheckingOutType type;
	public String getTakerUnit() {
		return takerUnit;
	}
	public void setTakerUnit(String takerUnit) {
		this.takerUnit = takerUnit;
	}
	public String getTaker() {
		return taker;
	}
	public void setTaker(String taker) {
		this.taker = taker;
	}
	public String getVouchersNumber() {
		return vouchersNumber;
	}
	public void setVouchersNumber(String vouchersNumber) {
		this.vouchersNumber = vouchersNumber;
	}
	public GUID getCheckedOutPerson() {
		return checkedOutPerson;
	}
	public void setCheckedOutPerson(GUID checkedOutPerson) {
		this.checkedOutPerson = checkedOutPerson;
	}
	public String getCheckedOutPersonName() {
		return checkedOutPersonName;
	}
	public void setCheckedOutPersonName(String checkedOutPersonName) {
		this.checkedOutPersonName = checkedOutPersonName;
	} 
	public long getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(long checkOutDate) {
		this.checkOutDate = checkOutDate;
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
	public CheckingOutType getType() {
		return type;
	}
	public void setType(CheckingOutType type) {
		this.type = type;
	} 

}
