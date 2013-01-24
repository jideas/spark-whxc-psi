package com.spark.oms.publish.product.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * 产品规格唯一性检验
 */
public class VersionNameTask extends SimpleTask {

	private String category;
	private String productName;
	private String productBarCode;
	private String itemName;
	private boolean Status;
	
	public VersionNameTask(String category, String productName,
			String productBarCode, String itemName) {
		super();
		this.category = category;
		this.productName = productName;
		this.productBarCode = productBarCode;
		this.itemName = itemName;
	}
	
	public VersionNameTask(String productBarCode, String itemName) {
		super();
		this.productBarCode = productBarCode;
		this.itemName = itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
}