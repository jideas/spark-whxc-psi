package com.spark.psi.publish.base.partner.key;

import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>
 * 查询供应商列表（只获取简要信息）
 * </p>
 * 速度比较快
 * 
 * 
 * 
 * 
 * @version 2012-4-10
 */
public class GetShortSupplierListKey extends LimitKey {

	public GetShortSupplierListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
		this.searchText = "";
	}

	public GetShortSupplierListKey() {
		this(0, 20, false);
	}

	private SortField sortField = SortField.None;

	private boolean isOnlyJointVenture = false;

	private boolean hasLxcg;

	public boolean isHasLxcg() {
		return hasLxcg;
	}

	public void setHasLxcg(boolean hasLxcg) {
		this.hasLxcg = hasLxcg;
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
		None(""), //
		/** 客户名称 */
		CustomerName(""), Address(""); //

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}

	public boolean isOnlyJointVenture() {
		return isOnlyJointVenture;
	}

	public void setOnlyJointVenture(boolean isOnlyJointVenture) {
		this.isOnlyJointVenture = isOnlyJointVenture;
	}

}
