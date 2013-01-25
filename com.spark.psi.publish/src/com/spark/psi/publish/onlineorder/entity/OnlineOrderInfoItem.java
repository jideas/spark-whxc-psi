package com.spark.psi.publish.onlineorder.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 网上订单商品明细<BR>
 * 查询方法：<BR>
 * ID+List<OnlineOrderInfoItem>;
 *
 */
public interface OnlineOrderInfoItem {

	public GUID getId();
	public GUID getBillsId();
	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsNo();
	public String getGoodsName();
	public String getGoodsSpec();
	public String getUnit();
	public int getGoodsScale();
	public double getPrice();
	public double getCount();
	public double getDiscount();
	public double getDisAmount();
	public double getAmount();
	public GUID getPromotionId();
}
