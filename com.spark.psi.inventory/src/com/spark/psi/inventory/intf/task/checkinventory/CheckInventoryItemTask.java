package com.spark.psi.inventory.intf.task.checkinventory;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.internal.entity.CheckInventoryItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;

/**
 * 
 * <p>盘点明细Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class CheckInventoryItemTask extends Task<Method> {
	
//	private CheckInventoryItem CheckInventoryItemEntity;
	private List<CheckInventoryItem> list;

//	public void setCheckInventoryItemEntity(CheckInventoryItem checkInventoryItemEntity) {
//		CheckInventoryItemEntity = checkInventoryItemEntity;
//	}
//
//	public CheckInventoryItem getCheckInventoryItemEntity() {
//		return CheckInventoryItemEntity;
//	}

	public void setList(List<CheckInventoryItem> list) {
		this.list = list;
	}

	public List<CheckInventoryItem> getList() {
		return list;
	}
	
}
