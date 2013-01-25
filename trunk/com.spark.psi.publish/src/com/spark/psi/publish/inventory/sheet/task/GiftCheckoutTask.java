package com.spark.psi.publish.inventory.sheet.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

public class GiftCheckoutTask extends SimpleTask{

	private GUID partnerId;
	private GUID storeId;
	private String remark;
	private double amount;
	private String deliveryPerson;
	private String deliveryDepartment;
	private String voucherNumber;
	private List<GiftCheckoutTaskItem> items;
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
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public List<GiftCheckoutTaskItem> getItems() {
		return items;
	}
	public void setItems(List<GiftCheckoutTaskItem> items) {
		this.items = items;
	}
	public DistributeInventoryItem[] getDistributeInventoryItems() {
		return distributeInventoryItems;
	}
	public void setDistributeInventoryItems(DistributeInventoryItem[] distributeInventoryItems) {
		this.distributeInventoryItems = distributeInventoryItems;
	}
	
}
