package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * ���۶������
 * 
 */
public class SalesOrderDistributeTask extends
		Task<SalesOrderDistributeTask.Method> {

	/**
	 * ��������
	 */
	public enum Method {
		Start, Confirm, Cancel,Reset
	}

	/**
	 * ���۶���ID
	 */
	private GUID salesOrderId;

	/**
	 * �������б�
	 */
	private DistributionItem[] items;

	/**
	 * 
	 * @param salesOrderId
	 *            ���۶���ID
	 * @param items
	 *            �������б�
	 */
	public SalesOrderDistributeTask(GUID salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	/**
	 * 
	 * @param salesOrderId
	 *            ���۶���ID
	 * @param items
	 *            �������б�
	 */
	public SalesOrderDistributeTask(GUID salesOrderId, DistributionItem[] items) {
		this.salesOrderId = salesOrderId;
		this.items = items;
	}

	/**
	 * @return the salesOrderId
	 */
	public GUID getSalesOrderId() {
		return salesOrderId;
	}

	/**
	 * @return the items
	 */
	public DistributionItem[] getItems() {
		return items;
	}

	/**
	 * ��������Ŀ
	 * 
	 * @author Administrator
	 * 
	 */
	public final static class DistributionItem {

		/**
		 * ��������
		 */
		private long deliverDate;

		/**
		 * �ֿ�ID
		 */
		private GUID storeId;
		/**
		 * ֱ����־��ΪtrueΪֱ������
		 */
		private boolean isDirect = false;

		/**
		 * ��Ӧ�ֿ�ͳ������ڵ������Ʒ��Ŀ�б�
		 */
		private DistributionGoodsItem[] items;

		/**
		 * ���캯��
		 * 
		 * @param deliverDate
		 * @param storeId
		 * @param items
		 */
		public DistributionItem(long deliverDate, GUID storeId,
				DistributionGoodsItem[] items) {
			super();
			this.deliverDate = deliverDate;
			this.storeId = storeId;
			this.items = items;
		}

		/**
		 * ���캯��(ֱ��)
		 * 
		 * @param isDirect
		 */
		public DistributionItem(DistributionGoodsItem[] items) {
			super();
			this.items = items;
			this.isDirect = true;
		}

		/**
		 * 
		 * @return boolean
		 */
		public boolean isDirect() {
			return isDirect;
		}

		/**
		 * @return the deliverDate
		 */
		public long getDeliverDate() {
			return deliverDate;
		}

		/**
		 * @return the storeId
		 */
		public GUID getStoreId() {
			return storeId;
		}

		/**
		 * @return the items
		 */
		public DistributionGoodsItem[] getItems() {
			return items;
		}

	}

	/**
	 * ��Ʒ��Ŀ�������
	 */
	public final static class DistributionGoodsItem {
		/**
		 * ��Ӧ���۶����е�һ����Ʒ��Ŀ��ID
		 */
		private GUID salesGoodsItemId;

		/**
		 * ��������
		 */
		private double count;

		/**
		 * ���캯��
		 * 
		 * @param salesGoodsItemId
		 * @param count
		 */
		public DistributionGoodsItem(GUID salesGoodsItemId, double count) {
			super();
			this.salesGoodsItemId = salesGoodsItemId;
			this.count = count;
		}

		/**
		 * @return the salesGoodsItemId
		 */
		public GUID getSalesGoodsItemId() {
			return salesGoodsItemId;
		}

		/**
		 * @return the count
		 */
		public double getCount() {
			return count;
		}

	}

}
