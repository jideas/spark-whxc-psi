package com.spark.psi.publish.account.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.ReceiptStatus;

/**
 * �տ��¼�б��ѯKEY
 */
public class GetReceiptListKey extends LimitKey {

	public GetReceiptListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}


	private ReceiptStatus[] status;
	/**
	 * ��ѯʱ��
	 */
	private QueryTerm queryTerm;
	
	/**
	 * �����ֶ�
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
	 * �����ֶ�
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
