package com.spark.psi.publish.onlinereturn.entity;

import com.jiuqi.dna.core.type.GUID;

public interface OnlineReturnDet {
	public GUID getRECID();

	public GUID getSheetId();

	public GUID getOnlineBillsItemId();

	public GUID getGoodsId();

	public String getGoodsCode();

	public String getGoodsNo();

	public String getGoodsSpec();

	public String getGoodsUnit();

	public String getGoodsName();

	public double getCount();

	public double getPrice();

	public double getAmount();
	
	public double getBillsCount();
}
