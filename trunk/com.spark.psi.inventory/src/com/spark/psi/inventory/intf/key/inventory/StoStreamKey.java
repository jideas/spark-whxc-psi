package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

public class StoStreamKey{
	private String searchText;//��ѯ����
	private String sortField = "createdDate";//��������
	private String sortType = "desc";
	
	/**
	 * ��Ʒ����ID���ձ�ʶ���з��ࣩ
	 */
	private GUID goodsCategoryId;
	
	/**
	 * �ֿ�ID���ձ�ʶ���вֿ⣩
	 */
	private GUID storeId;

	/**
	 * ��ѯʱ�ڷ�Χ
	 */
	private long dateBegin,dateEnd;
	
	/**
	 * ��ѯƫ�ƣ���0��ʼ��
	 */
	private int offset;

	/**
	 * ��ѯ����
	 */
	private int count;

	/**
	 * �Ƿ��ѯ����
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
