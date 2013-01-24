package com.spark.psi.publish.inventory.sheet.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class AdjustCheckinTask extends SimpleTask{

	private GUID partnerId;
	private GUID storeId;
	private String remark;
	private double amount;
	private List<AdjustCheckinTaskItem> items;
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
	public List<AdjustCheckinTaskItem> getItems() {
		return items;
	}
	public void setItems(List<AdjustCheckinTaskItem> items) {
		this.items = items;
	}
	
}
