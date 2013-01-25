package com.spark.psi.query.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * 线上商品销售记录<BR>
 * 查询方法：<BR>
 * GetOnlineSalesListKey+OnlineSalesListEntity;
 *
 */
public interface OnlineSalesItem {

	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsNo();
	public String getGoodsName();
	public String getGoodsSpec();
	public String getUnit();
	public double getCount();
	public double getAmount();
}
