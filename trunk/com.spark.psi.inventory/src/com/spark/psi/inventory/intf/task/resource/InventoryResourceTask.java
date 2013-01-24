package com.spark.psi.inventory.intf.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.service.resource.InventoryEntity;

/**
 * <p>Î¬»¤¿â´æ×ÊÔ´Task</p>
 *
 */

public class InventoryResourceTask extends Task<InventoryResourceTask.Type> {

	public enum Type
	{
		INSERT,
		UPDATE
	}
	private InventoryEntity inventoryEntity;

	public InventoryResourceTask(InventoryEntity inventoryEntity) {
		this.inventoryEntity = inventoryEntity;
	}

	public InventoryEntity getInventoryEntity() {
		return inventoryEntity;
	}

	
}
