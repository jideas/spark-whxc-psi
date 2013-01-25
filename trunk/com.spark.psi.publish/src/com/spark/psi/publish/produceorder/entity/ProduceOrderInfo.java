package com.spark.psi.publish.produceorder.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ProduceOrderStatus;

/**
 * 生产订单详情
 *
 */
public interface ProduceOrderInfo {

	public GUID getId();
	public String getBillsNo();
	public long getPlanDate();
	public double getGoodsCount();
	public String getRemark();
	public String getCreator();
	public long getCreateDate();
	public long getFinishDate();
	public String getApprovePerson();
	public long getApproveDate();
	public String getRejectReason();
	public ProduceOrderStatus getStatus();
	public ProduceOrderInfoGoodsItem[] getGoods();
	public ProduceOrderInfoMaterialsItem[] getMaterials();
	public ReceivedLog[] getReceivedLogs();
	public ReturnedLog[] getReturnedLogs();
	
	public interface ReceivedLog
	{
		public GUID getStoreId();
		public String getStoreName();
		public String getCreator();
		
		public Item[] getItems();
		public interface Item
		{
			public String getMaterialName();

			public String getMaterialSpec();

			public String getMaterialUnit();

			public int getScale();

			public double getPlanCount();
			public double getRealCount();
		}
		
	}
	
	public interface ReturnedLog
	{
		public GUID getStoreId();
		public String getStoreName();
		public String getCreator();
		
		public Item[] getItems();
		public interface Item
		{
			public String getMaterialName();

			public String getMaterialSpec();

			public String getMaterialUnit();

			public int getScale();

			public double getPlanCount();
			public double getRealCount();
		}
	}
}
