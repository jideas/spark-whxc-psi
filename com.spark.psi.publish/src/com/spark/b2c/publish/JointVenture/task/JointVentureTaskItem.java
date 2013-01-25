package com.spark.b2c.publish.JointVenture.task;

import com.jiuqi.dna.core.type.GUID;

public class JointVentureTaskItem {
	private GUID goodsId;
	private GUID sheetId;
	private String sheetNo;
	private double count;

	public GUID getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}

	public GUID getSheetId() {
		return sheetId;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

}
