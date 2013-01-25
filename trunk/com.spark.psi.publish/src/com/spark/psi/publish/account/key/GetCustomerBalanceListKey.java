package com.spark.psi.publish.account.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryScope;

/**
 * ��ѯ�ͻ��������key
 * 
 */
public class GetCustomerBalanceListKey extends LimitKey {

	/**
	 * ��ѯ��Χ
	 */
	private QueryScope queryScope;
	
	private SortField sortField;
	/**
	 * ���캯��
	 */
	public GetCustomerBalanceListKey() {
		super(0, 0, false); // ����ҳ
	}

	/**
	 * 
	 * @return
	 */
	public QueryScope getQueryScope() {
		return this.queryScope;
	}

	/**
	 * @param queryScope
	 *            the queryScope to set
	 */
	public void setQueryScope(QueryScope queryScope) {
		this.queryScope = queryScope;
	}
	
	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * �����ֶ�
	 */
	public static enum SortField {

		/**
		 * ������
		 */
		None(""), 
		/** �ͻ����� */
		PartnerName(""), 
		/** ��� */
		Balance("");
		
		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
