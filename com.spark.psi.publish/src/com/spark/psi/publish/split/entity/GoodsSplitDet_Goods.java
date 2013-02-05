package com.spark.psi.publish.split.entity;

import com.jiuqi.dna.core.type.GUID;

public interface GoodsSplitDet_Goods {

	public String getGoodsCode();

	public String getGoodsNo();

	public GUID getRECID();

	public GUID getGoodsId();

	public GUID getBillId();

	public double getGcount();

	public String getReason();

	public String getGoodsName();

	public String getGoodsSpec();

	public String getGoodsUnit();

}
