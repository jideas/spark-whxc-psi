package com.spark.psi.inventory.internal.key;

public class GetReportLossListKey {
	private String searchKey;
	private long beginTime;
	private long endTime;
	
	private int count;
	private int offSet;
	private String sortColumn;
	private String sortType;
	public enum ViewStatus {
		submitting, approvling, finished
	}
	
	private ViewStatus viewStatus;
	
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getOffSet() {
		return offSet;
	}
	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public ViewStatus getViewStatus() {
		return viewStatus;
	}
	public void setViewStatus(ViewStatus viewStatus) {
		this.viewStatus = viewStatus;
	}
	
}
