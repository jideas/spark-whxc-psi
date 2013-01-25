package com.spark.psi.publish.base.goods.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>
 * 商品条目列表查询
 * </p>
 * 
 */
public class GetGoodsInfoListKey extends LimitKey {

	/**
	 * 分类Id
	 */
	private GUID cateoryId;

	/**
	 * 是否只查询没有设置价格的商品
	 */
	private boolean nopriceOnly = false;

	private SortField sortField;

	/**
	 * 是否只查询设置了价格的商品
	 */
	private boolean setPriceOnley = false;

	/**
	 * 
	 */
	private GoodsStatus status = GoodsStatus.PART_SALE;

	private boolean isJointVenture = false;

	private boolean queryAll;

	public GetGoodsInfoListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	public GetGoodsInfoListKey() {
		super(0, 10, false);
	}

	public GUID getCateoryId() {
		return cateoryId;
	}

	public void setCateoryId(GUID cateoryId) {
		this.cateoryId = cateoryId;
	}

	public boolean isNopriceOnly() {
		return nopriceOnly;
	}

	public boolean isQueryAll() {
		return queryAll;
	}

	public void setQueryAll(boolean queryAll) {
		this.queryAll = queryAll;
	}

	public void setNopriceOnly(boolean nopriceOnly) {
		this.nopriceOnly = nopriceOnly;
	}

	public GoodsStatus getStatus() {
		return status;
	}

	public void setStatus(GoodsStatus status) {
		this.status = status;
	}

	public void setJointVenture(boolean isJointVenture) {
		this.isJointVenture = isJointVenture;
	}

	public boolean isJointVenture() {
		return isJointVenture;
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public boolean isSetPriceOnley() {
		return setPriceOnley;
	}

	public void setSetPriceOnley(boolean setPriceOnley) {
		this.setPriceOnley = setPriceOnley;
	}

	public static enum SortField {
		/**
		 * 不排序
		 */
		None("createDate"), //

		code("code"), name("name");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}

}
