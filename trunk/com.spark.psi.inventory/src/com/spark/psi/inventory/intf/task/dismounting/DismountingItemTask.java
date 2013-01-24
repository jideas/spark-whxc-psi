package com.spark.psi.inventory.intf.task.dismounting;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.internal.entity.DismountingItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;

public class DismountingItemTask extends Task<Method>{
	
	private DismountingItem dismountingItem;
	private List<DismountingItem> list;
	private GUID storeId;
	private GUID sheetId;
	private String sheetNumber;

	public GUID getSheetId() {
		return sheetId;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	public String getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(String sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public void setDismountingItemEntity(DismountingItem dismountingItem) {
		this.dismountingItem = dismountingItem;
	}

	public DismountingItem getDismountingItem() {
		return dismountingItem;
	}

	public void setList(List<DismountingItem> list) {
		this.list = list;
	}

	public List<DismountingItem> getList() {
		return list;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public GUID getStoreId() {
		return storeId;
	}
	
	
}
