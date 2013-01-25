package com.spark.psi.query.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 成品入库单查询实体<BR>
 * 查询方法：<BR>
 * GoodsCheckInListEntity+GetGoodsCheckInListKey;
 *
 */
public interface GoodsCheckInItem {

	public GUID getSheetId();
	public String getSheetNo();
	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsName();
	public String getUnit();
	public double getCost();
	public double getAmount();
	public double getCount();
	public double getStandardCost();
	public double getStandardAmount();
	public GUID getProduceSheetId();
	public String getProduceSheetNo();
	public String getDepartment();
	public boolean isNeedProduce();
	public long getCreateDate();
}
