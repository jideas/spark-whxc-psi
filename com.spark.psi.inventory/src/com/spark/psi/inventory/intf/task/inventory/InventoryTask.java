/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.service.resource.InventoryEntity;

/**
 * @author zhongxin
 *
 */
public class InventoryTask extends Task<InventoryTask.Method> {
	public enum Method {
		/** 新增 */
		ADD,
		/** 修改库存初始化信息 */
		MODIFY_INIT_INFO, 
		/** 删除库存初始化信息 */
		DELETE_INIT_INFO,
		/** 修改库存信息 */
		MODIFY_STORAGE_INFO,
		/** 修改商品裤上、下限*/
		MODIFY_STORE_UPPER_FLOOR,
	}
	private InventoryEntity storageEntity;
	public InventoryEntity getStorageEntity() {
		return storageEntity;
	}
	public void setStorageEntity(InventoryEntity storageEntity) {
		this.storageEntity = storageEntity;
	}
	
	
}
