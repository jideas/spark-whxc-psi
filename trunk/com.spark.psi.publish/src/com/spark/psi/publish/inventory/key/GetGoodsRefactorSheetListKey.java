package com.spark.psi.publish.inventory.key;

import com.spark.psi.publish.LimitKey;

/**
 * 查询商品拆装历史列表
 */
public class GetGoodsRefactorSheetListKey extends LimitKey {

	/**
	 * 构造函数
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetGoodsRefactorSheetListKey(int offset, int count,
			boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public SortField getSortField() {
		return sortField;
	}

	private SortField sortField;
	
	/**
	 * 排序字段
	 */
	public static enum SortField {
		None(""), //
		RefactorDate("dismDate"), //
		SheetNumber("dismOrdNo"), //
		StoreName("storeName"), //
		CreatorName("createPerson");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
