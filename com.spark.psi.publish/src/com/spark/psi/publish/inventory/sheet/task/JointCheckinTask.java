package com.spark.psi.publish.inventory.sheet.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

public class JointCheckinTask extends SimpleTask {

	private GUID storeId;
	private String remark;
	private List<JointCheckinTaskItem> items;
	private DistributeInventoryItem[] inventoryItems;

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getRemark() {
		return remark;
	}

	public DistributeInventoryItem[] getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(DistributeInventoryItem[] inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<JointCheckinTaskItem> getItems() {
		return items;
	}

	public void setItems(List<JointCheckinTaskItem> items) {
		this.items = items;
	}
}
