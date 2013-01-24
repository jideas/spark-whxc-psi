package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 商品或者其他物品在各个仓库的分布情况<br>
 * 查询方法：<br>
 * (1)根据GetGoodsInventorySummaryKey查询GoodsOrKitInventorySummary对象
 * (1)根据GetKitInventorySummaryKey查询GoodsOrKitInventorySummary对象
 */
public interface GoodsOrKitInventorySummary {

	/**
	 * 获取库存情况列表
	 */
	public SummaryItem[] getItems();

	/**
	 * 分布情况
	 */
	public static interface SummaryItem {

		/**
		 * 获取仓库ID
		 */
		public GUID getStoreId();

		/**
		 * 获取仓库名称
		 */
		public String getStoreName();

		/**
		 * 获取库存数量
		 */
		public double getCount();

	}

}
