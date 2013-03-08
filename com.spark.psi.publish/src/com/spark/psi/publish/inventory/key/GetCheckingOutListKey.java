package com.spark.psi.publish.inventory.key;

import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

/**
 * ��ѯ���ⵥ�б�Key
 * 
 */
public class GetCheckingOutListKey extends LimitKey {

	/**
	 * ʱ�ڷ�Χ
	 */
	private QueryTerm queryTerm;

	/**
	 * ���ⵥ״̬
	 */
	private CheckingOutStatus status;

	/**
	 * ���ⵥ����
	 */
	private CheckingOutType type;

	/**
	 * 
	 */
	private SortField sortField;

	private boolean realGoods = false;
	
	private boolean goodsSplit = false;

	public boolean isGoodsSplit() {
		return goodsSplit;
	}

	public void setGoodsSplit(boolean goodsSplit) {
		this.goodsSplit = goodsSplit;
	}

	/**
	 * ���캯��
	 */
	public GetCheckingOutListKey() {
		super(0, 0, false);
	}

	/**
	 * ���캯��
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetCheckingOutListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	/**
	 * @return the queryTerm
	 */
	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

	public boolean isRealGoods() {
		return realGoods;
	}

	public void setRealGoods(boolean realGoods) {
		this.realGoods = realGoods;
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
	public CheckingOutStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(CheckingOutStatus status) {
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public CheckingOutType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(CheckingOutType type) {
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
		SheetNumber("t.sheetNo"), //
		RelatedNumber("t.relaBillsNo"), //
		CreateDate("t.createDate"), //
		PlanCheckoutDate("t.checkoutDate"), //
		LastCheckoutDate("t.checkoutDate"), //
		StoreName("t.storeNamePY"), //
		status("t.status"), //
		Type("t.checkoutType"), //
		CheckoutEmployees("collate_gbk(t.checkoutPersonName)");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
