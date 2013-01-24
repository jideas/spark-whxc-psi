
package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>库存调拨明细</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class AllocateInventoryItem{
	private GUID id;
	private GUID allocateId;
	private GUID stockId;
	private String stockName;
	private String stockNo;
	private String stockCode;
	private String stockSpec;
	private String unit;
	private double ableCount;
	private double allocateCount;
	private GUID creatorId;
	private long createDate;
	private int stockScale;//精度
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getAllocateId() {
		return allocateId;
	}
	public void setAllocateId(GUID allocateId) {
		this.allocateId = allocateId;
	}
	public GUID getStockId() {
		return stockId;
	}
	public void setStockId(GUID stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockSpec() {
		return stockSpec;
	}
	public void setStockSpec(String stockSpec) {
		this.stockSpec = stockSpec;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getAbleCount() {
		return ableCount;
	}
	public void setAbleCount(double ableCount) {
		this.ableCount = ableCount;
	}
	public double getAllocateCount() {
		return allocateCount;
	}
	public void setAllocateCount(double allocateCount) {
		this.allocateCount = allocateCount;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public int getStockScale() {
		return stockScale;
	}
	public void setStockScale(int stockScale) {
		this.stockScale = stockScale;
	}
	
	
}
