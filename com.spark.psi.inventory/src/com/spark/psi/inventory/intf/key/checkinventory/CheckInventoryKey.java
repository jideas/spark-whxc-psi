package com.spark.psi.inventory.intf.key.checkinventory;

import com.jiuqi.dna.core.type.GUID;

public class CheckInventoryKey {

	private String searceText;
	private String sortField; 
	private String sortType;
	private GUID guid;

	/**
	 * 查询偏移（从0开始）
	 */
	private int offset;

	/**
	 * 查询数量
	 */
	private int count;

	/**
	 * 是否查询总数
	 */
	private boolean queryTotal;

	private boolean isBoss;
	private boolean isManager;
	private boolean isEmployee;

	public boolean isBoss() {
		return isBoss;
	}

	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(boolean queryTotal) {
		this.queryTotal = queryTotal;
	}

	public GUID getGuid() {
		return guid;
	}

	public void setGuid(GUID guid) {
		this.guid = guid;
	}

	public void setSearceText(String searceText) {
		this.searceText = searceText;
	}

	public String getSearceText() {
		return searceText;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortField() {
		return sortField;
	}

}
