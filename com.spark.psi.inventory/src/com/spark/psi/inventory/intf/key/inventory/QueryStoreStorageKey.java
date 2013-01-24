/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-15       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * @author zhongxin
 *
 */
public class QueryStoreStorageKey {
//	public final static GUID allStore = GUID.valueOf("2222222222222222222222222222222");
	private GUID tenantsGuid;
	private GUID storeGuid;
	private InventoryType storageType;
	//是否初始化
	private Boolean isInit = null;
	//指定商品
	private GUID goodsGuid;
	
	private boolean isShowZero = true;
	
	/**
	 * 查询指定仓库的库存信息
	 * @param tenantsGuid 租户GUID
	 * @param storeGuid 要查询的仓库GUID
	 * @param storageType 库存类型（StorageType.GOODSSTORAGE or StorageType.OTHERSTORAGE）
	 */
	public QueryStoreStorageKey(GUID tenantsGuid, GUID storeGuid, InventoryType storageType) {
		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
		this.storageType = storageType;
	}
	/**
	 * 查询指定仓库的库存信息
	 * @param tenantsGuid 租户GUID
	 * @param storeGuid 要查询的仓库GUID
	 */
	public QueryStoreStorageKey(GUID tenantsGuid, GUID storeGuid) {
		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
	}
	/**
	 * 查询指定仓库的库存信息
	 * @param tenantsGuid 租户GUID
	 * @param storeGuid 要查询的仓库GUID
	 * @param isInit 是否初始化
	 */
	public QueryStoreStorageKey(GUID tenantsGuid, GUID storeGuid, boolean isInit) {
		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
		this.isInit = isInit;
	}
	public GUID getStoreGuid() {
		return storeGuid;
	}
	public InventoryType getStorageType() {
		return storageType;
	}
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}

	public Boolean isInit() {
		return isInit;
	}
	public void setInit(Boolean isInit) {
		this.isInit = isInit;
	}
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	/**
	 * 设置查询指定商品的GUID
	 * @param goodsGuid
	 */
	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}
	public boolean isShowZero() {
		return isShowZero;
	}
	/**
	 * 设置是否返回库存数量为0的商品库存信息
	 * @param isShowZero
	 */
	public void setShowZero(boolean isShowZero) {
		this.isShowZero = isShowZero;
	}
	
	
}
