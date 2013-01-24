package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * 销售订单配货
 * 
 */
public class SalesOrderDistributeTask extends
		Task<SalesOrderDistributeTask.Method> {

	/**
	 * 操作方法
	 */
	public enum Method {
		Start, Confirm, Cancel,Reset
	}

	/**
	 * 销售订单ID
	 */
	private GUID salesOrderId;

	/**
	 * 配货结果列表
	 */
	private DistributionItem[] items;

	/**
	 * 
	 * @param salesOrderId
	 *            销售订单ID
	 * @param items
	 *            配货结果列表
	 */
	public SalesOrderDistributeTask(GUID salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	/**
	 * 
	 * @param salesOrderId
	 *            销售订单ID
	 * @param items
	 *            配货结果列表
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
	 * 配货结果条目
	 * 
	 * @author Administrator
	 * 
	 */
	public final static class DistributionItem {

		/**
		 * 出库日期
		 */
		private long deliverDate;

		/**
		 * 仓库ID
		 */
		private GUID storeId;
		/**
		 * 直供标志（为true为直供需求）
		 */
		private boolean isDirect = false;

		/**
		 * 对应仓库和出库日期的配货商品条目列表
		 */
		private DistributionGoodsItem[] items;

		/**
		 * 构造函数
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
		 * 构造函数(直供)
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
	 * 商品条目配货数据
	 */
	public final static class DistributionGoodsItem {
		/**
		 * 对应销售订单中的一个商品条目的ID
		 */
		private GUID salesGoodsItemId;

		/**
		 * 出库数量
		 */
		private double count;

		/**
		 * 构造函数
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
