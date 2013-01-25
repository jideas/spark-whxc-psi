package com.spark.psi.publish.produceorder.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 生产订单商品明细
 *
 */
public interface ProduceOrderInfoGoodsItem {

	public GUID getId();
	public GUID getBillsId();
	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsNo();
	public String getGoodsName();
	public String getGoodsSpec();
	public String getUnit();
	public int getGoodsScale();
	public double getCount();
	public GUID getBomId();
	public double getFinishedCount();
}
