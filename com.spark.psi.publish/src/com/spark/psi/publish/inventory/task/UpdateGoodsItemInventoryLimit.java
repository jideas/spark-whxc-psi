package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ThresholdControlType;

/**
 * 更新指定商品在指定仓库的库存上下限
 */
public class UpdateGoodsItemInventoryLimit extends SimpleTask {

	/**
	 * 商品ID
	 */
	private GUID goodsItemId;

	/**
	 * 阈值控制类型
	 */
	private ThresholdControlType thresholdControlType;

	/**
	 * 在多个仓库的阈值设置情况
	 */
	private Item[] items;

	/**
	 * 构造函数
	 * 
	 * @param goodsItemId
	 * @param thresholdControlType
	 * @param items
	 */
	public UpdateGoodsItemInventoryLimit(GUID goodsItemId,
			ThresholdControlType thresholdControlType, Item... items) {
		super();
		this.goodsItemId = goodsItemId;
		this.thresholdControlType = thresholdControlType;
		this.items = items;
	}

	/**
	 * @return the goodsItemId
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	/**
	 * @return the thresholdControlType
	 */
	public ThresholdControlType getThresholdControlType() {
		return thresholdControlType;
	}

	/**
	 * @return the items
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * 在一个仓库的阈值情况
	 */
	public final static class Item {
		/**
		 * 仓库ID
		 */
		private GUID storeId;

		/**
		 * 库存数量上限
		 */
		private double countUpperLimit;

		/**
		 * 库存数量下限
		 */
		private double countLowerLimit;

		/**
		 * 库存金额上限
		 */
		private double amountUpperLimit;

		/**
		 * @return the storeId
		 */
		public GUID getStoreId() {
			return storeId;
		}

		/**
		 * @param storeId
		 *            the storeId to set
		 */
		public void setStoreId(GUID storeId) {
			this.storeId = storeId;
		}

		/**
		 * @return the countUpperLimit
		 */
		public double getCountUpperLimit() {
			return countUpperLimit;
		}

		/**
		 * @param countUpperLimit
		 *            the countUpperLimit to set
		 */
		public void setCountUpperLimit(double countUpperLimit) {
			this.countUpperLimit = countUpperLimit;
		}

		/**
		 * @return the countLowerLimit
		 */
		public double getCountLowerLimit() {
			return countLowerLimit;
		}

		/**
		 * @param countLowerLimit
		 *            the countLowerLimit to set
		 */
		public void setCountLowerLimit(double countLowerLimit) {
			this.countLowerLimit = countLowerLimit;
		}

		/**
		 * @return the amountUpperLimit
		 */
		public double getAmountUpperLimit() {
			return amountUpperLimit;
		}

		/**
		 * @param amountUpperLimit
		 *            the amountUpperLimit to set
		 */
		public void setAmountUpperLimit(double amountUpperLimit) {
			this.amountUpperLimit = amountUpperLimit;
		}

	}

}
