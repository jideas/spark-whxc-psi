package com.spark.psi.publish.base.config.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 保存销售人员的信用额度信息
 */
public class SaveSalesmanCreditTask extends SimpleTask {

	/**
	 * 所有销售人员额度设置列表
	 */
	private CreditItem[] items;

	/**
	 * 构造函数
	 * 
	 * @param items
	 */
	public SaveSalesmanCreditTask(CreditItem[] items) {
		super();
		this.items = items;
	}

	/**
	 * @return the items
	 */
	public CreditItem[] getItems() {
		return items;
	}

	/**
	 * 单个销售人员的额度设置情况
	 */
	public final static class CreditItem {

		/**
		 * 销售人员ID
		 */
		private GUID salesmanId;

		/**
		 * 单个客户可设置的信用额度上限
		 */
		private double customerCreditLimit;

		/**
		 * 可设置的客户最长账期
		 */
		private int customerCreditDayLimit;

		/**
		 * 可设置的总信用额度
		 */
		private double availableTotalCreditLimit;

		/**
		 * 订单审批限额
		 */
		private double orderApprovalLimit;

		/**
		 * @return the salesmanId
		 */
		public GUID getSalesmanId() {
			return salesmanId;
		}

		/**
		 * @param salesmanId
		 *            the salesmanId to set
		 */
		public void setSalesmanId(GUID salesmanId) {
			this.salesmanId = salesmanId;
		}

		/**
		 * @return the customerCreditLimit
		 */
		public double getCustomerCreditLimit() {
			return customerCreditLimit;
		}

		/**
		 * @param customerCreditLimit
		 *            the customerCreditLimit to set
		 */
		public void setCustomerCreditLimit(double customerCreditLimit) {
			this.customerCreditLimit = customerCreditLimit;
		}

		/**
		 * @return the customerCreditDayLimit
		 */
		public int getCustomerCreditDayLimit() {
			return customerCreditDayLimit;
		}

		/**
		 * @param customerCreditDayLimit
		 *            the customerCreditDayLimit to set
		 */
		public void setCustomerCreditDayLimit(int customerCreditDayLimit) {
			this.customerCreditDayLimit = customerCreditDayLimit;
		}

		/**
		 * @return the availableTotalCreditLimit
		 */
		public double getAvailableTotalCreditLimit() {
			return availableTotalCreditLimit;
		}

		/**
		 * @param availableTotalCreditLimit
		 *            the availableTotalCreditLimit to set
		 */
		public void setAvailableTotalCreditLimit(
				double availableTotalCreditLimit) {
			this.availableTotalCreditLimit = availableTotalCreditLimit;
		}

		/**
		 * @return the orderApprovalLimit
		 */
		public double getOrderApprovalLimit() {
			return orderApprovalLimit;
		}

		/**
		 * @param orderApprovalLimit
		 *            the orderApprovalLimit to set
		 */
		public void setOrderApprovalLimit(double orderApprovalLimit) {
			this.orderApprovalLimit = orderApprovalLimit;
		}

	}

}
