package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.GoodsOrKitInventorySummary;

/**
 * 商品或者其他物品在各个仓库的分布情况<br>
 * 查询方法：<br>
 * (1)根据GetGoodsInventorySummaryKey查询GoodsOrKitInventorySummary对象
 * (1)根据GetKitInventorySummaryKey查询GoodsOrKitInventorySummary对象
 */
public class GoodsOrKitInventorySummaryImpl implements GoodsOrKitInventorySummary{

	/**
	 * 获取库存情况列表
	 */
	private SummaryItemImpl[] items;

	/**
	 * 分布情况
	 */
	public static class SummaryItemImpl implements SummaryItem {

		/**
		 * 获取仓库ID
		 */
		private GUID storeId;

		/**
		 * 仓库名称
		 */
		private String storeName;

		/**
		 * 获取库存数量
		 */
		private double count;

		public GUID getStoreId() {
			return storeId;
		}

		public void setStoreId(GUID storeId) {
			this.storeId = storeId;
		}

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public double getCount() {
			return count;
		}

		public void setCount(double count) {
			this.count = count;
		}
		

	}

	public void setItems(SummaryItemImpl[] items) {
		this.items = items;
	}

	public SummaryItem[] getItems() {
		return items;
	}

}
