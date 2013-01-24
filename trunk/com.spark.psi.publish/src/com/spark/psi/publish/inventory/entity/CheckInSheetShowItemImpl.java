package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.checkin.entity.CheckInSheetShowItem;

public class CheckInSheetShowItemImpl implements CheckInSheetShowItem {

	private GUID id;
	private String sheetNo;
	private CheckingInType type;
	private long checkinDate;
	private String relaBillsNo;
	private String storeName;
	private String checkinPersonName;

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

	public CheckingInType getType() {
		return type;
	}

	public void setType(CheckingInType type) {
		this.type = type;
	}

	public long getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(long checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getRelaBillsNo() {
		return relaBillsNo;
	}

	public void setRelaBillsNo(String relaBillsNo) {
		this.relaBillsNo = relaBillsNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCheckinPersonName() {
		return checkinPersonName;
	}

	public void setCheckinPersonName(String checkinPersonName) {
		this.checkinPersonName = checkinPersonName;
	}
}
