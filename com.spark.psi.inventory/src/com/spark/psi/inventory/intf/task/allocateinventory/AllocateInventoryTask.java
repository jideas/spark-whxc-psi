
package com.spark.psi.inventory.intf.task.allocateinventory;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.internal.entity.AllocateInventory;
import com.spark.psi.inventory.internal.entity.AllocateInventoryItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;

/**
 * 
 * <p>调拨Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class AllocateInventoryTask extends Task<Method> {
	
	private AllocateInventory allocateEntity;
	private GUID allocateInfoGuid;
	private List<AllocateInventoryItem> list;
	private int count;
	
	public AllocateInventory getAllocateEntity() {
		return allocateEntity;
	}
	public void setAllocateEntity(AllocateInventory allocateEntity) {
		this.allocateEntity = allocateEntity;
	}
	public GUID getAllocateInfoGuid() {
		return allocateInfoGuid;
	}
	public void setAllocateInfoGuid(GUID allocateInfoGuid) {
		this.allocateInfoGuid = allocateInfoGuid;
	}
	public void setList(List<AllocateInventoryItem> list) {
		this.list = list;
	}
	public List<AllocateInventoryItem> getList() {
		return list;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	
	
}
