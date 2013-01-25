package com.spark.psi.query.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 生产订单查询实体<BR>
 * 查询方法：<BR>
 * ProduceListEntity+GetProduceListKey;
 *
 */
public interface ProduceItem {

	public GUID getBillsId();
	public String getBillsNo();
	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsName();
	public String getUnit();
	public double getCount();
	public double getFinishedCount();
	public long getCreateDate();
	public long getPlanDate();
	public Log[] getLogs();
	public Item[] getItems();
	
	public interface Log
	{
		public GUID getBillsId();
		public GUID getGoodsId();
		public String getGoodsCode();
		public String getGoodsName();
		public String getUnit();
		public double getCount();
		public GUID getCreatorId();
		public String getCreator();
		public long getCreateDate();
	}
	public interface Item
	{
		public GUID getBillsId();
		public GUID getMaterialId();
		public String getMaterialCode();
		public String getMaterialName();
		public String getUnit();
		public double getCount();
		public double getCost();
		public double getTotalCost();
		public String getMaterialNo();
		public String getSpec();
	}
}
