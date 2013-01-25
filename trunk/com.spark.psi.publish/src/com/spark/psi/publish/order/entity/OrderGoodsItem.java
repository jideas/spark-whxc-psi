package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 订单商品明细
 */
public interface OrderGoodsItem {

	public GUID getGoodsItemId();

	public String getGoodsCode();

	public String getGoodsNo();

	public String getName();

	public String getSpec();

	public String getUnit();

	public int getScale();

	public double getPrice();

	public double getCount();

	public double getAmount();

	public GUID getId();

}
