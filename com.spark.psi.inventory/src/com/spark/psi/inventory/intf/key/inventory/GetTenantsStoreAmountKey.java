/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-12-20       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获得租户当前的库存金额
 * @author zhongxin
 *
 */
public class GetTenantsStoreAmountKey {
	private GUID tenantsGuid;
	public GetTenantsStoreAmountKey(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
}
