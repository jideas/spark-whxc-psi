package com.spark.psi.publish.inventory.checkin.entity;

import com.jiuqi.dna.core.type.GUID;

public interface CheckInBaseInfoItem {
	public GUID getId();

	public GUID getSheetId();

	public GUID getGoodsId();

	public String getGoodsCode();

	public String getGoodsNo();

	public String getGoodsName();

	public String getGoodsSpec();

	public String getUnit();

	public int getScale();

	public double getPrice();

	public double getAmount();

	public double getRealCount();
}
