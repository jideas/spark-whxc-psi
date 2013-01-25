package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.LimitKey;

/**
 * 查询指定商品条目在各个仓库的库存列表
 * 
 */
public class GetInventoryItemKey extends LimitKey {

	public GetInventoryItemKey(InventoryType inventoryType, int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
		this.inventoryType = inventoryType;
	}

	/**
	 * 商品分类Id
	 */
	private GUID goodsCategoryId;
	/**
	 * 仓库Id
	 */
	private GUID storeId;
	
	private InventoryType inventoryType;
	
	private SortField sortField;

	public GUID getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(GUID goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	
	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public SortField getSortField() {
		return sortField;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}




	/**
	 * 排序字段
	 */
	public static enum SortField {
		None(""), //
		code("code"), //
		number("number"), //
		name("name"), //
		unit("unit"), //
		spec("spec"),
		shelfLife("shelfLife"),
		count("count");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
