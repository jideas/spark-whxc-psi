package com.spark.psi.inventory.intf.task.inventory;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.service.resource.InventoryDetEntity;
import com.spark.psi.inventory.service.resource.InventoryEntity;
import com.spark.psi.publish.InventoryType;

/**
 *
 */
public class InventoryInitTask extends Task<InventoryInitTask.Method> {
	public enum Method {
		/** 保存 */
		SAVE,
		/** 将初始化数据写入库存(启用仓库时用) */
		ENABLE,
	}
	
	private List<InventoryEntity> inventoryList;
	private GUID storeId;
	private InventoryType type;
	private List<InventoryDetEntity> inventoryDetList;
	/**
	 * 
	 * @param storeId 仓库ID
	 */
	public InventoryInitTask(GUID storeId)
	{
		this.storeId = storeId;
	}
	public void setInventoryList(List<InventoryEntity> inventoryList) {
		this.inventoryList = inventoryList;
	}
	public List<InventoryEntity> getInventoryList() {
		return inventoryList;
	}
	
	public GUID getStoreId() {
		return storeId;
	}
	public void setType(InventoryType type) {
		this.type = type;
	}
	public InventoryType getType() {
		return type;
	}
	public void setInventoryDetList(List<InventoryDetEntity> inventoryDetList) {
		this.inventoryDetList = inventoryDetList;
	}
	public List<InventoryDetEntity> getInventoryDetList() {
		return inventoryDetList;
	}
	
	
}
