package com.spark.psi.publish.inventory.sheet.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class CheckInKitTask extends SimpleTask {

	private String goodsFrom;
	private GUID storeId;
	private String remark;
	private List<CheckInKitTaskItem> items;

	public String getGoodsFrom() {
		return goodsFrom;
	}

	public void setGoodsFrom(String goodsFrom) {
		this.goodsFrom = goodsFrom;
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

	public List<CheckInKitTaskItem> getItems() {
		return items;
	}

	public void setItems(List<CheckInKitTaskItem> items) {
		this.items = items;
	}
}
