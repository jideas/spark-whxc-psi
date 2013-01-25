package com.spark.psi.publish.account.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryScope;

/**
 * 查询客户往来情况key
 * 
 */
public class GetCustomerBalanceListKey extends LimitKey {

	/**
	 * 查询范围
	 */
	private QueryScope queryScope;
	
	private SortField sortField;
	/**
	 * 构造函数
	 */
	public GetCustomerBalanceListKey() {
		super(0, 0, false); // 不分页
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
	 * 排序字段
	 */
	public static enum SortField {

		/**
		 * 不排序
		 */
		None(""), 
		/** 客户名称 */
		PartnerName(""), 
		/** 余额 */
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
