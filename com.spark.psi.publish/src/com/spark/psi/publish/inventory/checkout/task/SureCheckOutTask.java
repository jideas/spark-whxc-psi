package com.spark.psi.publish.inventory.checkout.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

public class SureCheckOutTask extends SimpleTask {

	private GUID id;
	private String deliveryPerson;
	private String deliveryDepartment;
	private String voucherNumber;
	private String goodsFrom;// 物品来源
	private String goodsUse;
	private List<SureCheckOutTaskItem> items;
	private DistributeInventoryItem[] inventoryItems;
	private boolean success ;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getDeliveryPerson() {
		return deliveryPerson;
	}

	public String getGoodsFrom() {
		return goodsFrom;
	}

	public void setGoodsFrom(String goodsFrom) {
		this.goodsFrom = goodsFrom;
	}

	public String getGoodsUse() {
		return goodsUse;
	}

	public void setGoodsUse(String goodsUse) {
		this.goodsUse = goodsUse;
	}

	public DistributeInventoryItem[] getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(DistributeInventoryItem[] inventoryItems) {
		this.inventoryItems = inventoryItems;
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

	public List<SureCheckOutTaskItem> getItems() {
		return items;
	}

	public void setItems(List<SureCheckOutTaskItem> items) {
		this.items = items;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
