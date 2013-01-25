package com.spark.order.purchase.intf.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.key.SelectKey;

public class SelectPurchaseOvertimeAmountKey extends SelectKey{
	private final GUID tenantsGuid;

	public SelectPurchaseOvertimeAmountKey(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
}
