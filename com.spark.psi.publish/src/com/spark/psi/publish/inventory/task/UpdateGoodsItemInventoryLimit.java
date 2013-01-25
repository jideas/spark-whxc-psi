package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ThresholdControlType;

/**
 * ����ָ����Ʒ��ָ���ֿ�Ŀ��������
 */
public class UpdateGoodsItemInventoryLimit extends SimpleTask {

	/**
	 * ��ƷID
	 */
	private GUID goodsItemId;

	/**
	 * ��ֵ��������
	 */
	private ThresholdControlType thresholdControlType;

	/**
	 * �ڶ���ֿ����ֵ�������
	 */
	private Item[] items;

	/**
	 * ���캯��
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
	 * ��һ���ֿ����ֵ���
	 */
	public final static class Item {
		/**
		 * �ֿ�ID
		 */
		private GUID storeId;

		/**
		 * �����������
		 */
		private double countUpperLimit;

		/**
		 * �����������
		 */
		private double countLowerLimit;

		/**
		 * ���������
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
