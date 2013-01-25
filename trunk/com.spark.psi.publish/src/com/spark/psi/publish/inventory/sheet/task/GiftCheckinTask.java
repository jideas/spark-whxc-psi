package com.spark.psi.publish.inventory.sheet.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

public class GiftCheckinTask extends SimpleTask{

	private GUID partnerId;
	private GUID storeId;
	private String remark;
	private double amount;
	private List<GiftCheckinTaskItem> items;
	private DistributeInventoryItem[] distributeInventoryItems;
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public List<GiftCheckinTaskItem> getItems() {
		return items;
	}
	public void setItems(List<GiftCheckinTaskItem> items) {
		this.items = items;
	}
	public DistributeInventoryItem[] getDistributeInventoryItems() {
		return distributeInventoryItems;
	}
	public void setDistributeInventoryItems(DistributeInventoryItem[] distributeInventoryItems) {
		this.distributeInventoryItems = distributeInventoryItems;
	}
	
}
