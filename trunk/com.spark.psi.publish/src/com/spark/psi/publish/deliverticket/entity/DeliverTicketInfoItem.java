package com.spark.psi.publish.deliverticket.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * 发货单商品明细
 *
 */
public interface DeliverTicketInfoItem {

	public GUID getId();
	public GUID getTicketId();
	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsNo();
	public String getGoodsName();
	public String getGoodsSpec();
	public String getUnit();
	public int getGoodsScale();
	public double getPrice();
	public double getCount();
	public double getDisRate();
	public double getDisAmount();
	public double getAmount();
}
