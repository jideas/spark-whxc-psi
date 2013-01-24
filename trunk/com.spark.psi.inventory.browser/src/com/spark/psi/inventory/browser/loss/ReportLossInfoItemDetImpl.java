package com.spark.psi.inventory.browser.loss;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.ReportLossInfoItemDet;

public class ReportLossInfoItemDetImpl implements ReportLossInfoItemDet {

	private double count;
	private GUID id;
	private long produceDate;
	private GUID reportLossItemId;
	private GUID shelfId;
	private int shelfNo;
	private GUID stockId;
	private int tiersNo;
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public long getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(long produceDate) {
		this.produceDate = produceDate;
	}
	public GUID getReportLossItemId() {
		return reportLossItemId;
	}
	public void setReportLossItemId(GUID reportLossItemId) {
		this.reportLossItemId = reportLossItemId;
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
	public GUID getStockId() {
		return stockId;
	}
	public void setStockId(GUID stockId) {
		this.stockId = stockId;
	}
	public int getTiersNo() {
		return tiersNo;
	}
	public void setTiersNo(int tiersNo) {
		this.tiersNo = tiersNo;
	}
	

}
