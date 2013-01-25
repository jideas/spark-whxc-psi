package com.spark.psi.publish.inventory.key;

import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

/**
 * ��ѯ��ⵥ�б�Key
 * 
 */
public class GetCheckingInListKey extends LimitKey {

	private boolean realGoods = false;
	/**
	 * ʱ�ڷ�Χ
	 */
	private QueryTerm queryTerm;

	/**
	 * ��ⵥ״̬
	 */
	private CheckingInStatus status;

	/**
	 * ��ⵥ����
	 */
	private CheckingInType type;

	/**
	 * 
	 */
	private SortField sortField;

	/**
	 * ���캯��
	 */
	public GetCheckingInListKey() {
		super(0, 0, false);
	}

	/**
	 * ���캯��
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetCheckingInListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	public boolean isRealGoods() {
		return realGoods;
	}

	public void setRealGoods(boolean realGoods) {
		this.realGoods = realGoods;
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
	 * @return the status
	 */
	public CheckingInStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(CheckingInStatus status) {
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public CheckingInType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(CheckingInType type) {
		this.type = type;
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
	 * �����ֶ�
	 */
	public static enum SortField {
		None(""), //
		/**
		 * ���ݱ��
		 */
		SheetNumber("t.sheetNo"), //

		/**
		 * ��ص��ݱ��
		 */
		RelatedNumber("t.relaBillsNo"), //
		/**
		 * ��������
		 */
		CreateDate("t.createDate"), //
		/**
		 * Ԥ���������
		 */
		PlanCheckinDate("t.checkinDate"), StoreName("t.storeNamePY"), //
		status("t.status"), //
		Type("t.sheetType"), //
		LastCheckinDate("t.checkinDate"), CheckinEmployees("collate_gbk(t.checkinPersonName)");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
