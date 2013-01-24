package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.GoodsOrKitInventorySummary;

/**
 * ��Ʒ����������Ʒ�ڸ����ֿ�ķֲ����<br>
 * ��ѯ������<br>
 * (1)����GetGoodsInventorySummaryKey��ѯGoodsOrKitInventorySummary����
 * (1)����GetKitInventorySummaryKey��ѯGoodsOrKitInventorySummary����
 */
public class GoodsOrKitInventorySummaryImpl implements GoodsOrKitInventorySummary{

	/**
	 * ��ȡ�������б�
	 */
	private SummaryItemImpl[] items;

	/**
	 * �ֲ����
	 */
	public static class SummaryItemImpl implements SummaryItem {

		/**
		 * ��ȡ�ֿ�ID
		 */
		private GUID storeId;

		/**
		 * �ֿ�����
		 */
		private String storeName;

		/**
		 * ��ȡ�������
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
