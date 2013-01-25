package com.spark.psi.publish.account.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.ReceiptStatus;

/**
 * 收款记录列表查询KEY
 */
public class GetReceiptListKey extends LimitKey {

	public GetReceiptListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}


	private ReceiptStatus[] status;
	/**
	 * 查询时期
	 */
	private QueryTerm queryTerm;
	
	/**
	 * 排序字段
	 */
	private SortField sortField;

	

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

	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	}

	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

	public void setStatus(ReceiptStatus... status) {
		this.status = status;
	}

	public ReceiptStatus[] getStatus() {
		return status;
	}

	/**
	 * 排序字段
	 */
	public static enum SortField {

		None("t.createDate"), //
		PartnerName("t.partnerName"), //
		ReceiptsNo("t.receiptNo"), //
		Amount("t.amount"); //

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
