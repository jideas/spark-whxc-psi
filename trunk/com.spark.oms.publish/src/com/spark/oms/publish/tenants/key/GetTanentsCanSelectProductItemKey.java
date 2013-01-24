package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获取租户可以选择的产品规格条目
 * 
 *   ProductItemInfo & GetTanentsCanSelectProductItem
 */
public class GetTanentsCanSelectProductItemKey {
	//产品条码
	private String productCode;
	
	//已选条目(续单使用)
	private GUID itemGuid;
	
	//租户信息
	private GUID tenantsId;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public GUID getItemGuid() {
		return itemGuid;
	}

	public void setItemGuid(GUID itemGuid) {
		this.itemGuid = itemGuid;
	}

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	
}
