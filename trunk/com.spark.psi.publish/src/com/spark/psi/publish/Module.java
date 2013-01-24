package com.spark.psi.publish;

/**
 * 
 * <p>业务模块</p>
 *


 *
 
 * @version 2012-3-8
 */
public enum Module{
	
	SalesOrder("01","销售订单"),
	SalesReturn("02","销售退货"),
	PurchaseOrder("03","采购订单"),
	PurchaseReturn("04","采购退货"),
	InventoryAllocate("05","库存调拨"),
	Promotion("06","商品促销");
	
	private String code;
	private String name;

	private Module(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	
}
