package com.spark.b2c.publish.JointVenture.entity;

import com.jiuqi.dna.core.type.GUID;

public interface JointSettlementInfoItem {

	public GUID getId();
	public GUID getGoodsId();
	public String getSheetId();
	public String getGoodsCode();
	public String getGoodsNo();
	public String getGoodsSpec();
	public String getGoodsUnit();
	public String getGoodsName();
	public double getGoodsPrice();
	public double getCount();
	public double getAmount();
	public double getPercentage();
	public double getPercentageAmount();
}
