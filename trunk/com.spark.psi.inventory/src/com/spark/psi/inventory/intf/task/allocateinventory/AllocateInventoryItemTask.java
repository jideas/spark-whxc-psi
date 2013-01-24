
package com.spark.psi.inventory.intf.task.allocateinventory;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.internal.entity.AllocateInventoryItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;

/**
 * 
 * <p>������ϸTask</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class AllocateInventoryItemTask extends Task<Method> {
	
	
	private AllocateInventoryItem allocateInventoryItemEntity;
    private GUID allocateInventoryID;
	
	public void setAllocateInventoryItemEntity(
			AllocateInventoryItem allocateInventoryItemEntity) {
		this.allocateInventoryItemEntity = allocateInventoryItemEntity;
	}
	public AllocateInventoryItem getAllocateInventoryItemEntity() {
		return allocateInventoryItemEntity;
	}
	public void setAllocateInventoryID(GUID allocateInventoryID) {
		this.allocateInventoryID = allocateInventoryID;
	}
	public GUID getAllocateInventoryID() {
		return allocateInventoryID;
	}
	
}
