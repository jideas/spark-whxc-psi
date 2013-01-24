/**
 * 
 */
package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 库存流水创建事件
 *
 */
public class InventoryLogEvent  extends Event{

	private GUID inventoryLogId;

	public void setInventoryLogId(GUID inventoryLogId) {
		this.inventoryLogId = inventoryLogId;
	}

	public GUID getInventoryLogId() {
		return inventoryLogId;
	}
}
