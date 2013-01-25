package com.spark.psi.publish.inventory.key;

import com.spark.psi.publish.LimitKey;

/**
 * ��ѯ��Ʒ��װ��ʷ�б�
 */
public class GetGoodsRefactorSheetListKey extends LimitKey {

	/**
	 * ���캯��
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
	 * �����ֶ�
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
