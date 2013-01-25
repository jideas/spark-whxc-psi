package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.SortType;

/**
 * 查询库存流水Key
 */
public class GetInventoryLogKey extends LimitKey {

	/**
	 * 商品分类ID（空标识所有分类）
	 */
	private GUID goodsCategoryId;

	/**
	 * 仓库ID（空标识所有仓库）
	 */
	private GUID storeId;
	
	private SortField sortField;
	
	private InventoryType inventoryType = InventoryType.Materials;
	
	private long dateBegin,dateEnd;

	@Override
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(long dateBegin) {
		this.dateBegin = dateBegin;
	}

	public long getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(long dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public boolean isQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(boolean queryTotal) {
		this.queryTotal = queryTotal;
	}

	@Override
	public SortType getSortType() {
		return sortType;
	}

	@Override
	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	public void setGoodsCategoryId(GUID goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	} 

	/**
	 * 构造函数
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetInventoryLogKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	/**
	 * @return the goodsCategoryId
	 */
	public GUID getGoodsCategoryId() {
		return goodsCategoryId;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	} 

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	/**
	 * 排序字段
	 */
	public static enum SortField {
		None(""), //
		RelatedNumber("orderNo"),
		GoodsCode("code"), 
		GoodsName("name"), 
		Properties("properties"), 
		Unit("unit"), 
		CheckedInCount("instoCount"),
		CheckedInAmount("instoAmount"),
		CheckedOutCount("outstoCount"),
		CheckedOutAmount("outstoAmount"),
		Type("logType"), 
		Date("createdDate");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
