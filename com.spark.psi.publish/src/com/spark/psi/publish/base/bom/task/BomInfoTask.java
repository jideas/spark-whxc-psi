package com.spark.psi.publish.base.bom.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;

public class BomInfoTask extends SimpleTask {

	private GUID id;

	private GUID goodsItemId;

	private String goodsNo;

	private String remark;
	
	private BOM_STATUS status;

	private List<BomInfoTaskItem> items;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<BomInfoTaskItem> getItems() {
		return items;
	}

	public void setItems(List<BomInfoTaskItem> items) {
		this.items = items;
	}

	public BOM_STATUS getStatus() {
		return status;
	}

	public void setStatus(BOM_STATUS status) {
		this.status = status;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

}
