package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

public class DistributeInventoryItemDet {
	private GUID id;//	��¼��ʶ
	private GUID shelfId;//	��λ��ʶ
	private int shelfNo;//	��λ���
	private int tiersNo;//	������ڲ���
	private GUID stockId;//	�����ʶ
	private double count;//	�������
	private long produceDate;//	��������
	private double distributeCount; // ����仯����
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
