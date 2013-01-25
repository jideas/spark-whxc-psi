package com.spark.psi.publish.base.config.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ����������Ա�����ö����Ϣ
 */
public class SaveSalesmanCreditTask extends SimpleTask {

	/**
	 * ����������Ա��������б�
	 */
	private CreditItem[] items;

	/**
	 * ���캯��
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
	 * ����������Ա�Ķ���������
	 */
	public final static class CreditItem {

		/**
		 * ������ԱID
		 */
		private GUID salesmanId;

		/**
		 * �����ͻ������õ����ö������
		 */
		private double customerCreditLimit;

		/**
		 * �����õĿͻ������
		 */
		private int customerCreditDayLimit;

		/**
		 * �����õ������ö��
		 */
		private double availableTotalCreditLimit;

		/**
		 * ���������޶�
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
