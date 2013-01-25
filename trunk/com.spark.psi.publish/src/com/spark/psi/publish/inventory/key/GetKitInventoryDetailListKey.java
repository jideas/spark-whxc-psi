package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

/**
 * 查询指定仓库或者所有仓库中其他物品的库存
 */
public class GetKitInventoryDetailListKey extends LimitKey {

	/**
	 * 仓库ID
	 */
	private GUID storeId;
	
	/**
	 * 指定的排序字段
	 */
	private SortField sortField;

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * @param storeId 仓库ID，空为查询全部仓库
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
	 * 排序字段
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