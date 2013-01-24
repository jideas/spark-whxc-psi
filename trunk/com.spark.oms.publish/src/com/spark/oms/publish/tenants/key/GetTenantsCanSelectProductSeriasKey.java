package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.OrderType;

/**
 * 获取租户可选择的产品系列
 */
public class GetTenantsCanSelectProductSeriasKey {
	
	private GUID tenantsGuid;
	
	private OrderType orderType;
	
	private String productSerial;
	
	private GUID orderId;
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}

	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public String getProductSerial() {
		return productSerial;
	}

	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}

	public GUID getOrderId() {
		return orderId;
	}

	public void setOrderId(GUID orderId) {
		this.orderId = orderId;
	}
	
}
