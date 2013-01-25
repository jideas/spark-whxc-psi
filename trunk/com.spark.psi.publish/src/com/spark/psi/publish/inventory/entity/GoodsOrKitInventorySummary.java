package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ����������Ʒ�ڸ����ֿ�ķֲ����<br>
 * ��ѯ������<br>
 * (1)����GetGoodsInventorySummaryKey��ѯGoodsOrKitInventorySummary����
 * (1)����GetKitInventorySummaryKey��ѯGoodsOrKitInventorySummary����
 */
public interface GoodsOrKitInventorySummary {

	/**
	 * ��ȡ�������б�
	 */
	public SummaryItem[] getItems();

	/**
	 * �ֲ����
	 */
	public static interface SummaryItem {

		/**
		 * ��ȡ�ֿ�ID
		 */
		public GUID getStoreId();

		/**
		 * ��ȡ�ֿ�����
		 */
		public String getStoreName();

		/**
		 * ��ȡ�������
		 */
		public double getCount();

	}

}
