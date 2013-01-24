package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.OrderType;

/**
 * 获取租户可选择的商品
 */
public class GetTenantsCanSelectProductKey {
	
	/**
	 * 租户标识
	 */
	private GUID tenantsRECID;
	
	/**
	 * 产品系列标识
	 */
	private String serial;
	
	/**
	 * 选择范围
	 * @return
	 */
	private OrderType orderType;
	
	/**
	 * 产品条目
	 * @return
	 */
	private String productItemCode;
	
	
	
	public String getProductItemCode() {
		return productItemCode;
	}

	public void setProductItemCode(String productItemCode) {
		this.productItemCode = productItemCode;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public GUID getTenantsRECID() {
		return tenantsRECID;
	}

	public void setTenantsRECID(GUID tenantsRECID) {
		this.tenantsRECID = tenantsRECID;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

}
