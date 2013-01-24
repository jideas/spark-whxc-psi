/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.finance.invoice.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.publish.account.key;

import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.PaymentStatus;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.QueryTerm;

/**
 * 付款记录列表查询KEY
 * 
 * @version 2011-11-28
 */

public class GetPaymentListKey extends LimitKey {

	/**
	 * 查询时期
	 */
	private QueryTerm queryTerm;

	/**
	 * 排序字段
	 */
	private SortField sortField = SortField.None;

	/**
	 * 高级条件
	 */
	private AdvanceCondition advanceCondition;
	
	private PaymentStatus[] status;

	/**
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetPaymentListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	/**
	 * @return the queryTerm
	 */
	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

	/**
	 * @param queryTerm
	 *            the queryTerm to set
	 */
	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	}

	/**
	 * @return the sortField
	 */
	public SortField getSortField() {
		return sortField;
	}

	/**
	 * @param sortField
	 *            the sortField to set
	 */
	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * @return the advanceCondition
	 */
	public AdvanceCondition getAdvanceCondition() {
		return advanceCondition;
	}

	/**
	 * @param advanceCondition
	 *            the advanceCondition to set
	 */
	public void setAdvanceCondition(AdvanceCondition advanceCondition) {
		this.advanceCondition = advanceCondition;
	}

	public void setStatus(PaymentStatus... status) {
		this.status = status;
	}

	public PaymentStatus[] getStatus() {
		return status;
	}

	/**
	 * 高级搜索条件
	 * 
	 */
	public static class AdvanceCondition {

		/**
		 * 付款目标
		 */
		private String paymentTargetName;

		/**
		 * 开始日期
		 */
		private long startDate;

		/**
		 * 结束日期
		 */
		private long endDate;

		/**
		 * 付款人
		 */
		private String payer;

		/**
		 * 付款类型代码
		 */
		private PaymentType[] paymentTypes;

		/**
		 * 查询最小金额
		 */
		private Double minAmount;

		/**
		 * 查询最大金额
		 */
		private Double maxAmount;

		/**
		 * 付款方式代码
		 */
		private DealingsWay[] paymentWay;

		/**
		 * @return the paymentTargetName
		 */
		public String getPaymentTargetName() {
			return paymentTargetName;
		}

		/**
		 * @param paymentTargetName
		 *            the paymentTargetName to set
		 */
		public void setPaymentTargetName(String paymentTargetName) {
			this.paymentTargetName = paymentTargetName;
		}

		/**
		 * @return the startDate
		 */
		public long getStartDate() {
			return startDate;
		}

		/**
		 * @param startDate
		 *            the startDate to set
		 */
		public void setStartDate(long startDate) {
			this.startDate = startDate;
		}

		/**
		 * @return the endDate
		 */
		public long getEndDate() {
			return endDate;
		}

		/**
		 * @param endDate
		 *            the endDate to set
		 */
		public void setEndDate(long endDate) {
			this.endDate = endDate;
		}

		/**
		 * @return the payer
		 */
		public String getPayer() {
			return payer;
		}

		/**
		 * @param payer
		 *            the payer to set
		 */
		public void setPayer(String payer) {
			this.payer = payer;
		}


		public PaymentType[] getPaymentTypes() {
			return paymentTypes;
		}

		public void setPaymentTypes(PaymentType... paymentTypes) {
			this.paymentTypes = paymentTypes;
		}

		/**
		 * @return the minAmount
		 */
		public Double getMinAmount() {
			return minAmount;
		}

		/**
		 * @param minAmount
		 *            the minAmount to set
		 */
		public void setMinAmount(Double minAmount) {
			this.minAmount = minAmount;
		}

		/**
		 * @return the maxAmount
		 */
		public Double getMaxAmount() {
			return maxAmount;
		}

		/**
		 * @param maxAmount
		 *            the maxAmount to set
		 */
		public void setMaxAmount(Double maxAmount) {
			this.maxAmount = maxAmount;
		}

		public DealingsWay[] getPaymentWay() {
			return paymentWay;
		}

		public void setPaymentWay(DealingsWay... paymentWay) {
			this.paymentWay = paymentWay;
		}

		
	}

	/**
	 * 排序字段
	 */
	public static enum SortField {

		None("t.createDate"), //
		PayDate("t.payDate"), //
		PaymentNo("t.paymentNo"), //
		PartnerName("t.partnerName"), //
		PaymentType("t.paymentType"), //
		Amount("t.amount"), //
		Remark("remark");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
