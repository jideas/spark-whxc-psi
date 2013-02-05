package com.spark.psi.inventory.split.publish;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Goods;

public class GoodsSplitDet_GoodsImpl implements GoodsSplitDet_Goods {
	private GUID RECID;
	private GUID goodsId;
	private GUID billId;
	private double gcount;
	private String reason;
	private String goodsName;
	private String goodsSpec;
	private String goodsUnit;

	public GUID getRECID() {
		return RECID;
	}

	public GUID getGoodsId() {
		return goodsId;
	}

	public GUID getBillId() {
		return billId;
	}

	public double getGcount() {
		return gcount;
	}

	public String getReason() {
		return reason;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getGoodsSpec() {
		return goodsSpec;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}

	public void setBillId(GUID billId) {
		this.billId = billId;
	}

	public void setGcount(double gcount) {
		this.gcount = gcount;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

}
