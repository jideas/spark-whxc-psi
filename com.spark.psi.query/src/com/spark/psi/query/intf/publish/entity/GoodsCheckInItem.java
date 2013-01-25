package com.spark.psi.query.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ��ⵥ��ѯʵ��<BR>
 * ��ѯ������<BR>
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
