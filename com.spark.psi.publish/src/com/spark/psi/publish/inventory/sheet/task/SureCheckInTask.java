package com.spark.psi.publish.inventory.sheet.task;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

public class SureCheckInTask extends SimpleTask {

	private GUID id;

	private String buyPerson, goodsFrom;
	private long buyDate;

	private List<CheckInSheetTaskItem> items;
	
	private DistributeInventoryItem[] inventoryItems;

	private boolean success=true;
	private List<CheckInError> errors;

	public void addError(CheckInError error) {
		if (null == errors) {
			errors = new ArrayList<CheckInError>();
		}
		errors.add(error);
	}

	public DistributeInventoryItem[] getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(DistributeInventoryItem[] inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public String getBuyPerson() {
		return buyPerson;
	}

	public void setBuyPerson(String buyPerson) {
		this.buyPerson = buyPerson;
	}

	public String getGoodsFrom() {
		return goodsFrom;
	}

	public void setGoodsFrom(String goodsFrom) {
		this.goodsFrom = goodsFrom;
	}

	public long getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(long buyDate) {
		this.buyDate = buyDate;
	}

	public static enum CheckInError {
		StoreError("�ֿ�ͣ�û��̵��У����飡"), DataChangedError("Ҫ���µ������Ѿ������˸ı䣬���飡"), GoodsCountError("��Ʒ����������ⵥ���������飡"), BillsError(
				"��ص��ݲ����ڣ����飡");

		private String message;

		public String getMessage() {
			return message;
		}

		private CheckInError(String message) {
			this.message = message;
		}
	}

	public SureCheckInTask(GUID sheetId, List<CheckInSheetTaskItem> itemList) {
		this.id = sheetId;
		this.items = itemList;
	}

	public List<CheckInError> getErrors() {
		return errors;
	}

	public GUID getId() {
		return id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<CheckInSheetTaskItem> getItems() {
		return items;
	}

}
