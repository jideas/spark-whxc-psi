package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

/**
 * ��ѯָ���ֿ�������вֿ���������Ʒ�Ŀ��
 */
public class GetKitInventoryDetailListKey extends LimitKey {

	/**
	 * �ֿ�ID
	 */
	private GUID storeId;
	
	/**
	 * ָ���������ֶ�
	 */
	private SortField sortField;

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * @param storeId �ֿ�ID����Ϊ��ѯȫ���ֿ�
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetKitInventoryDetailListKey(GUID storeId, int offset, int count,
			boolean queryTotal) {
		super(offset, count, queryTotal);
		this.storeId = storeId;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	
	/**
	 * �����ֶ�
	 */
	public static enum SortField {
		None(""),
		KitName("name"),
		KitDesc("properties"),
		Unit("unit"),
		Count("count");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}
}