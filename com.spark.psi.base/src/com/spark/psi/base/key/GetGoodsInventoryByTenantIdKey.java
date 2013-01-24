package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得租户商品库存情况</p>
 *


 *
 
 * @version 2012-3-9
 */
public class GetGoodsInventoryByTenantIdKey {

	private GUID tenantId;
	
	public GUID getTenantId() {
		return tenantId;
	}

	/**
	 * 查询指定租户的库存情况
	 * @param tenantId  租户id
	 */
	public GetGoodsInventoryByTenantIdKey(GUID tenantId){
		this.tenantId = tenantId;
    }

}
