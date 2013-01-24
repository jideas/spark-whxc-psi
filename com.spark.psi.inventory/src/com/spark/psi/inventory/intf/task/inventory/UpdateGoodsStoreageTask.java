/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-18       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * @author zhongxin
 *
 */
public class UpdateGoodsStoreageTask extends SimpleTask {
	private GUID storageGuid;
	
	private GUID tenantsGuid;
	private GUID storeGuid;
	private GUID goodsGuid;
	
	private double count;
	private double amount;
	
	/**
	 * 
	 * @return
	 */
	public GUID getStorageGuid() {
		return storageGuid;
	}
	public void setStorageGuid(GUID storageGuid) {
		this.storageGuid = storageGuid;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	public GUID getStoreGuid() {
		return storeGuid;
	}
	public void setStoreGuid(GUID storeGuid) {
		this.storeGuid = storeGuid;
	}
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
