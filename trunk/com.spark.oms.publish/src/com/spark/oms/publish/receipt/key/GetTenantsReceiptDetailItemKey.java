package com.spark.oms.publish.receipt.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 租户缴款明细列表
 * GetTenantsReceiptDetailItemKey & TenantsReceiptDetailItem
 */
public class GetTenantsReceiptDetailItemKey {
	
	private GUID TenantsId;

	public GUID getTenantsId() {
		return TenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		TenantsId = tenantsId;
	}
	
	

}
