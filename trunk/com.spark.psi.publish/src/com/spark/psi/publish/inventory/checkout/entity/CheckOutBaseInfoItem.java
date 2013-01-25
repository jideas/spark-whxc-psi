package com.spark.psi.publish.inventory.checkout.entity;

import com.jiuqi.dna.core.type.GUID;

public interface CheckOutBaseInfoItem {
	public GUID getRECID();

	public GUID getSheetId();

	public GUID getGoodsId();

	public String getGoodsCode();

	public String getGoodsNo();

	public String getGoodsName();

	public String getGoodsSpec();

	public String getUnit();

	public int getScale();

	public double getPrice();

	public double getAvgCost();

	public double getAmount();

	public double getRealCount();

	public String getCreatePerson();

	public long getCreateDate();

}
