package com.spark.psi.publish.deliver.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * ÅäËÍµ¥¶©µ¥Ã÷Ï¸
 *
 */
public interface DeliverInfoItem {

	public GUID getId();

	public GUID getDeliverSheetId();
	public GUID getOnlineOrderId();

	public String getOnlineOrderNo();

	public String getMemberRealName();

	public double getOrderAmount();

	public Item[] getItems();
	
	public interface Item
	{
		public GUID getGoodsItemId();
		public String getGoodsName();
		public String getGoodsSpec();
		public double getCount();
	}
}
