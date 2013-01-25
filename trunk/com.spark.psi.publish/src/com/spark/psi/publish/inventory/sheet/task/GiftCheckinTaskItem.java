package com.spark.psi.publish.inventory.sheet.task;

import com.jiuqi.dna.core.type.GUID;

public class GiftCheckinTaskItem {

	public GiftCheckinTaskItem(GUID goodsId, double count) {
		this.goodsId = goodsId;
		this.count = count;
	}

	private GUID goodsId;
	// private String
	// goodsNo,goodsCode,goodsName,goodsUnit,goodsSpec,goodsScale;
	private double count;

	public GUID getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	} 
}
