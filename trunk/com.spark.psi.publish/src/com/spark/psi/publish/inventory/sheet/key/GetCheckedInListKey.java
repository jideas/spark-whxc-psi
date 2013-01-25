package com.spark.psi.publish.inventory.sheet.key;

import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

public class GetCheckedInListKey  extends LimitKey{

	/**
	 * 时期范围
	 */
	private QueryTerm queryTerm;

	/**
	 * 入库单类型
	 */
	private CheckingInType type;

	/**
	 * 
	 */
	private SortField sortField;

	/**
	 * 构造函数
	 */
	public GetCheckedInListKey() {
		super(0, 0, false);
	}

	/**
	 * 构造函数
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetCheckedInListKey(int offset, int count, boolean queryTotal) {
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
	 * 排序字段
	 */
	public static enum SortField {
		None(""), //
		/**
		 * 单据编号
		 */
		SheetNumber("t.sheetNo"), //
	
		/**
		 * 相关单据编号
		 */
		RelatedNumber("t.relaBillsNo"), //
		/**
		 * 创建日期
		 */
		CreateDate("t.createDate"), //
		/**
		 * 预计入库日期
		 */
		PlanCheckinDate("t.checkinDate"), //
		/**
		 * 
		 */
		LastCheckinDate("t.checkinDate"), //
		StoreName("t.storeNamePY"), //
		status("t.status"), //
		Type("t.sheetType"), //
		CheckinEmployees("t.checkinString");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
