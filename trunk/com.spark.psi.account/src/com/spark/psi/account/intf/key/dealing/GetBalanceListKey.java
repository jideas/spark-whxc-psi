/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.account.intf.key.dealing
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-29       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.key.dealing;

import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.SortType;

/**
 * <p>
 * �ͻ�/��Ӧ������б�Key
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author Wangtiancai
 * @version 2012-3-29
 */

public class GetBalanceListKey {

	/**
	 * ��ѯ��Χ
	 */
	private QueryScope queryScope;
	/**
	 * ��ѯƫ�ƣ���0��ʼ��
	 */
	private int offset;

	/**
	 * ��ѯ����
	 */
	private int count;

	/**
	 * �Ƿ��ѯ����
	 */
	private boolean queryTotal;

	/**
	 * ����ʽ
	 */
	private SortType sortType;

	/**
	 * �����ı�
	 */
	private String searchText;
	/**
	 * �ͻ� or ��Ӧ��
	 */
	private PartnerType partnerType;

	public GetBalanceListKey(PartnerType partnerType) {
		this.partnerType = partnerType;
	}

	public QueryScope getQueryScope() {
		return queryScope;
	}

	public void setQueryScope(QueryScope queryScope) {
		this.queryScope = queryScope;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(boolean queryTotal) {
		this.queryTotal = queryTotal;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public void setPartnerType(PartnerType partnerType) {
		this.partnerType = partnerType;
	}

	public PartnerType getPartnerType() {
		return partnerType;
	}

	private SortField sortField = SortField.None;

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
		Balance("amount");
		
		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
