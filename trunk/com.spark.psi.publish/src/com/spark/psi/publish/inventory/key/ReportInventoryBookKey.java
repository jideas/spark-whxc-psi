/**
 * 
 */
package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.SortType;

/**
 *
 */
public class ReportInventoryBookKey extends LimitKey {

//	public ReportInventoryBookKey(InventoryType inventoryType) {
//		this.inventoryType = inventoryType;
//	}
	
	public ReportInventoryBookKey(InventoryType inventoryType, int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
		this.inventoryType = inventoryType;
	}

	private GUID storeId;
	private long beginTime;
	private long endTime;
	private String searchKey;
	private GUID GoodsTypeId;
	private InventoryType inventoryType;

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	private SortField sortField;
	private SortType sortType;

	public enum SortField {
		goodsCode("goodsCode"),
		goodsNo("goodsNo"), //
		goodsName("goodsName"), //
		goodsAttr("goodsAttr"), //
		goodsUnit("goodsUnit"), //
		count_begin("count_begin"), //
		amount_begin("amount_begin"), //
		instoCount("instoCount"), //
		instoAmount("instoAmount"), //
		outstoCount("outstoCount"), //
		outstoAmount("outstoAmount"), //
		count_end("count_end"), //
		amount_end("amount_end");

		private String column;

		private SortField(String column) {
			this.column = column;
		}

		public String getColumn() {
			return column;
		}
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the beginTime
	 */
	public long getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime
	 *            the beginTime to set
	 */
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the searchKey
	 */
	public String getSearchKey() {
		return searchKey;
	}

	/**
	 * @param searchKey
	 *            the searchKey to set
	 */
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	/**
	 * @return the goodsTypeId
	 */
	public GUID getGoodsTypeId() {
		return GoodsTypeId;
	}

	/**
	 * @param goodsTypeId
	 *            the goodsTypeId to set
	 */
	public void setGoodsTypeId(GUID goodsTypeId) {
		GoodsTypeId = goodsTypeId;
	}
}
