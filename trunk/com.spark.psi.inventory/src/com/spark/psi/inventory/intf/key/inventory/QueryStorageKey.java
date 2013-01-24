/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-11       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * 根据指定的信息查询库存
 * @author zhongxin
 *
 */
public class QueryStorageKey {
	private GUID tenantsGuid;
	private GUID storeGuid;
	private GUID goodsGuid;
	
	/** 查询具体内容  */
	private boolean isInit;
	
	/**
	 * 查询指定商品在指定仓库的库存信息
	 * @param tenantsGuid 租户GUID
	 * @param goodsGuid 查询商品的GUID
	 * @param storeGuid 查询仓库的GUID
	 */
	public QueryStorageKey(GUID tenantsGuid, GUID goodsGuid, GUID storeGuid) {
		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
		this.goodsGuid = goodsGuid;
	}
	public GUID getStoreGuid() {
		return storeGuid;
	}
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public boolean isInit() {
		return isInit;
	}
	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}
	
	
}
