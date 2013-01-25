package com.spark.psi.publish.inventory.sheet.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

public class JointCheckoutTask extends SimpleTask {

	private GUID storeId;
	private String remark;
	private List<JointCheckoutTaskItem> items;
	private DistributeInventoryItem[] inventoryItems;
	private String deliveryPerson;
	private String deliveryDepartment;
	private String voucherNumber;

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

	public List<JointCheckoutTaskItem> getItems() {
		return items;
	}

	public void setItems(List<JointCheckoutTaskItem> items) {
		this.items = items;
	}

	public String getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	public String getDeliveryDepartment() {
		return deliveryDepartment;
	}

	public void setDeliveryDepartment(String deliveryDepartment) {
		this.deliveryDepartment = deliveryDepartment;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	} 
}
