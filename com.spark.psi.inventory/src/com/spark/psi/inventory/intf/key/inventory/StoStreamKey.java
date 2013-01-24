package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

public class StoStreamKey{
	private String searchText;//查询条件
	private String sortField = "createdDate";//排序列名
	private String sortType = "desc";
	
	/**
	 * 商品分类ID（空标识所有分类）
	 */
	private GUID goodsCategoryId;
	
	/**
	 * 仓库ID（空标识所有仓库）
	 */
	private GUID storeId;

	/**
	 * 查询时期范围
	 */
	private long dateBegin,dateEnd;
	
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

	private String inventoryType;
	
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public GUID getGoodsCategoryId() {
		return goodsCategoryId;
	}
	public void setGoodsCategoryId(GUID goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	} 
	public long getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(long dateBegin) {
		this.dateBegin = dateBegin;
	}
	public long getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(long dateEnd) {
		this.dateEnd = dateEnd;
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
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	public String getInventoryType() {
		return inventoryType;
	}
	
}
