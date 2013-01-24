package com.spark.psi.inventory.intf.key.checkinventory;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.internal.entity.CheckInventoryItem;

/**
 * 
 * <p>盘点明细Key</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class CheckInventoryItemKey {
	private CheckInventoryItem checkInventoryItemEntity;
	private GUID checkOrdGuid;
	private GUID goodsTypeGuid;
	private String orderName = "goodsName";
	private String order = "asc";
	private GUID tenantsGuid;
	private boolean isHistory = false;
	
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public GUID getCheckOrdGuid() {
		return checkOrdGuid;
	}

	public void setCheckOrdGuid(GUID checkOrdGuid) {
		this.checkOrdGuid = checkOrdGuid;
	}

	public void setGoodsTypeGuid(GUID goodsTypeGuid) {
		this.goodsTypeGuid = goodsTypeGuid;
	}

	public GUID getGoodsTypeGuid() {
		return goodsTypeGuid;
	}

	public void setHistory(boolean isHistory) {
		this.isHistory = isHistory;
	}

	public boolean isHistory() {
		return isHistory;
	}

	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}

	public GUID getTenantsGuid() {
		return tenantsGuid;
	}

	public void setCheckInventoryItemEntity(CheckInventoryItem checkInventoryItemEntity) {
		this.checkInventoryItemEntity = checkInventoryItemEntity;
	}

	public CheckInventoryItem getCheckInventoryItemEntity() {
		return checkInventoryItemEntity;
	}
	
	
	
}
