package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 创建库存调拨单
 */
public class CreateInventoryAllocateSheetTask extends SimpleTask {

	/**
	 * 调出仓库ID
	 */
	private GUID sourceStoreId;

	/**
	 * 调入仓库ID
	 */
	private GUID destinationStoreId;

	/**
	 * 原因
	 */
	private String cause;

	/**
	 * 调拨商品条目列表
	 */
	private AllocateStockItem[] items;
	
	private GUID sheetId;

	/**
	 * 
	 * @param sourceStoreId
	 * @param destinationStoreId
	 * @param cause
	 * @param items
	 */
	public CreateInventoryAllocateSheetTask(GUID sourceStoreId,
			GUID destinationStoreId, String cause, AllocateStockItem[] items) {
		super();
		this.sourceStoreId = sourceStoreId;
		this.destinationStoreId = destinationStoreId;
		this.cause = cause;
		this.items = items;
	}

	/**
	 * @return the sourceStoreId
	 */
	public GUID getSourceStoreId() {
		return sourceStoreId;
	}

	/**
	 * @return the destinationStoreId
	 */
	public GUID getDestinationStoreId() {
		return destinationStoreId;
	}

	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @return the items
	 */
	public AllocateStockItem[] getItems() {
		return items;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	public GUID getSheetId() {
		return sheetId;
	}

	/**
	 * 调拨商品条目
	 */
	public final static class AllocateStockItem {

		/**
		 * 商品条目ID
		 */
		private GUID goodsItemId;

		/**
		 * 调拨数量
		 */
		private double allocateCount;

		/**
		 * 构造函数
		 * 
		 * @param goodsItemId
		 * @param allocateCount
		 */
		public AllocateStockItem(GUID goodsItemId, double allocateCount) {
			super();
			this.goodsItemId = goodsItemId;
			this.allocateCount = allocateCount;
		}

		/**
		 * @return the goodsItemId
		 */
		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		/**
		 * @return the allocateCount
		 */
		public double getAllocateCount() {
			return allocateCount;
		}

	}
}
