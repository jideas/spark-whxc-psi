package com.spark.psi.publish.inventory.key;

import com.spark.psi.publish.LimitKey;

/**
 * ��ѯ����̵㵥�б�
 */
public class GetInventoryCountSheetListKey extends LimitKey {

	/**
	 * ���캯��
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetInventoryCountSheetListKey(int offset, int count,
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
		StartDate("startDate"), 
		EndDate("endDate"), 
		SheetNumber("checkOrdNo"), 
		Sheetstatus("checkOrdState"), 
		StoreName("storeName"), 
		CountProfit("checkProfit"), 
		CountLoss("checkDeficient");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}

}
