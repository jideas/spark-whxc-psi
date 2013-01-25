package com.spark.psi.publish.account.key;

import com.spark.psi.publish.LimitKey;


/**
 * 查询供应商往来情况key
 * 
 */
public class GetSupplierBalanceListKey extends LimitKey {

	private SortField sortField;
	
	/**
	 * 构造函数
	 */
	public GetSupplierBalanceListKey() {
		super(0, 0, true); // 不分页
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
		/** 名称 */
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
