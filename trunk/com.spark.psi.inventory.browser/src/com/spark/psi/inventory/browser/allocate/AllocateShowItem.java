package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.core.type.GUID;

public class AllocateShowItem {

	private GUID stockItemId;
	private String stockItemCode;
	private String stockItemNumber;
	private String stockItemName;
	private String stockSpec;
	private String stockItemUnit;
	private int scale;
	private double availableCount;
	private double allocateCount;

	
	public GUID getStockItemId() {
		return stockItemId;
	}

	public void setStockItemId(GUID goodsItemId) {
		this.stockItemId = goodsItemId;
	}

	public String getStockItemCode() {
		return stockItemCode;
	}

	public void setStockItemCode(String stockItemCode) {
		this.stockItemCode = stockItemCode;
	}
	
	public String getStockItemNumber() {
		return stockItemNumber;
	}

	public void setStockItemNumber(String stockItemNumber) {
		this.stockItemNumber = stockItemNumber;
	}

	public String getStockItemName() {
		return stockItemName;
	}

	public void setStockItemName(String stockItemName) {
		this.stockItemName = stockItemName;
	}

	public String getStockSpec() {
		return stockSpec;
	}

	public void setStockSpec(String stockSpec) {
		this.stockSpec = stockSpec;
	}

	public String getStockItemUnit() {
		return stockItemUnit;
	}

	public void setStockItemUnit(String stockItemUnit) {
		this.stockItemUnit = stockItemUnit;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public double getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(double availableCount) {
		this.availableCount = availableCount;
	}

	public double getAllocateCount() {
		return allocateCount;
	}

	public void setAllocateCount(double allocateCount) {
		this.allocateCount = allocateCount;
	}
}
