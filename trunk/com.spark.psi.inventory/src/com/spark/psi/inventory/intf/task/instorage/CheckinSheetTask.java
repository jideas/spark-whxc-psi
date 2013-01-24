package com.spark.psi.inventory.intf.task.instorage;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.intf.entity.instorage.mod.CheckInSheet;
import com.spark.psi.inventory.intf.entity.instorage.mod.CheckInSheetItem;

public class CheckinSheetTask extends SimpleTask{
	private CheckInSheet entity;
 
	private List<CheckInSheetItem> items;
	private boolean success;
	public CheckInSheet getEntity() {
		return entity;
	}
	public void setEntity(CheckInSheet entity) {
		this.entity = entity;
	}
	public List<CheckInSheetItem> getItems() {
		return items;
	}
	public void setItems(List<CheckInSheetItem> items) {
		this.items = items;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	} 
}
