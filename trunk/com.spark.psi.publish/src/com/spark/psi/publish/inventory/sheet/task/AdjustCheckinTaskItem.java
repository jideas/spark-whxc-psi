package com.spark.psi.publish.inventory.sheet.task;

import com.jiuqi.dna.core.type.GUID;

public class AdjustCheckinTaskItem {

	public AdjustCheckinTaskItem(GUID goodsId, double amount) {
		this.goodsId = goodsId;
		this.amount = amount;
	}

	private GUID goodsId;
	// private String
	// goodsNo,goodsCode,goodsName,goodsUnit,goodsSpec,goodsScale;
	private double amount;

	public GUID getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
