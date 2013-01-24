/**
 * 
 */
/**
 * 
 */
package com.spark.psi.inventory.intf.task.checkinventory;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.internal.entity.CheckInventory;
import com.spark.psi.inventory.internal.entity.CheckInventoryItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

/**
 * 
 * <p>盘点Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class CheckInventoryTask extends Task<Method> {
	
	
	private List<CheckInventoryItem> list;
	private CheckInventory checkInventoryEntity;
	private GUID sheetId;
	private int count;

	private DistributeInventoryItem[] inventorysAdd;
	private DistributeInventoryItem[] inventorysSub;
	public void setList(List<CheckInventoryItem> list) {
		this.list = list;
	}
	
	public List<CheckInventoryItem> getList() {
		return list;
	}

	public void setCheckInventoryEntity(CheckInventory checkInventoryEntity) {
		this.checkInventoryEntity = checkInventoryEntity;
	}

	public CheckInventory getCheckInventoryEntity() {
		return checkInventoryEntity;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	public GUID getSheetId() {
		return sheetId;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public DistributeInventoryItem[] getInventorysAdd() {
		return inventorysAdd;
	}

	public void setInventorysAdd(DistributeInventoryItem[] inventorysAdd) {
		this.inventorysAdd = inventorysAdd;
	}

	public DistributeInventoryItem[] getInventorysSub() {
		return inventorysSub;
	}

	public void setInventorysSub(DistributeInventoryItem[] inventorysSub) {
		this.inventorysSub = inventorysSub;
	}
}
