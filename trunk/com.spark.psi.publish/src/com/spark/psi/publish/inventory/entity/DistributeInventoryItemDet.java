package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

public class DistributeInventoryItemDet {
	private GUID id;//	记录标识
	private GUID shelfId;//	货位标识
	private int shelfNo;//	货位编号
	private int tiersNo;//	存货所在层数
	private GUID stockId;//	存货标识
	private double count;//	存货数量
	private long produceDate;//	生产日期
	private double distributeCount; // 存货变化数量
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
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
	public long getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(long produceDate) {
		this.produceDate = produceDate;
	}
	public double getDistributeCount() {
		return distributeCount;
	}
	public void setDistributeCount(double distributeCount) {
		this.distributeCount = distributeCount;
	}
	
}
