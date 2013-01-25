package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

public class InventoryAllocateItemDet {
	private GUID id;
	private GUID allocateItemId;
	private GUID sheetId;
	private GUID stockId;
	private String stockName;
	private GUID storeId;
	private double count;
	private GUID shelfId;
	private int shelfNo;
	private int tiersNo;
	private long produceDate;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getAllocateItemId() {
		return allocateItemId;
	}
	public void setAllocateItemId(GUID allocateItemId) {
		this.allocateItemId = allocateItemId;
	}
	public GUID getSheetId() {
		return sheetId;
	}
	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}
	public GUID getStockId() {
		return stockId;
	}
	public void setStockId(GUID stockId) {
		this.stockId = stockId;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public GUID getShelfId() {
		return shelfId;
	}
	public void setShelfId(GUID shelfId) {
		this.shelfId = shelfId;
	}
	public int getShelfNo() {
		return shelfNo;
	}
	public void setShelfNo(int shelfNo) {
		this.shelfNo = shelfNo;
	}
	public int getTiersNo() {
		return tiersNo;
	}
	public void setTiersNo(int tiersNo) {
		this.tiersNo = tiersNo;
	}
	public long getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(long produceDate) {
		this.produceDate = produceDate;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	
	
}
