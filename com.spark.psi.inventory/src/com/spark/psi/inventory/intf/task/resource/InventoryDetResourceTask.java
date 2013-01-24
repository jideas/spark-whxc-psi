package com.spark.psi.inventory.intf.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.service.resource.InventoryDetEntity;

/**
 * <p>维护库存明细资源Task</p>
 *
 */

public class InventoryDetResourceTask extends Task<InventoryDetResourceTask.Type> {

	public enum Type
	{
		INSERT,
		UPDATE
	}
	private InventoryDetEntity inventoryDetEntity;

	public InventoryDetResourceTask(InventoryDetEntity inventoryDetEntity) {
		this.inventoryDetEntity = inventoryDetEntity;
	}

	public InventoryDetEntity getInventoryDetEntity() {
		return inventoryDetEntity;
	}

	
}
