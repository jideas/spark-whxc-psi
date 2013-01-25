package com.spark.b2c.publish.JointVenture.entity;

import com.jiuqi.dna.core.type.GUID;

public interface JointVentureRecordItem {

	public GUID getRECID();

	public GUID getGoodsId();

	public GUID getSheetId();

	public GUID getSupplierId();

	public String getSupplierName();

	public String getShortName();

	public String getSupplierNamePY();

	public String getSheetNo();

	public String getGoodsCode();

	public String getGoodsNo();

	public String getGoodsSpec();

	public String getGoodsUnit();

	public String getGoodsName();

	public double getGoodsPrice();

	public double getCount();

	public double getAmount();

	public double getPercentage();

	public long getCreateDate();

	public boolean isAlreadySettlement();
}
