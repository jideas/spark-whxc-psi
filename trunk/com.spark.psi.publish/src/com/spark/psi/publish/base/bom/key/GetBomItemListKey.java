package com.spark.psi.publish.base.bom.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;

public class GetBomItemListKey extends LimitKey {

	public GetBomItemListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	public GetBomItemListKey() {
		super(0, 20, false);
		this.sortType = SortType.Desc;
	}

	private BOM_STATUS[] status;
	private SortField sortField = SortField.None;

	public BOM_STATUS[] getStatus() {
		return status;
	}

	public void setStatus(BOM_STATUS ...status) {
		this.status = status;
	}

	/**
	 * ÅÅÐò×Ö¶Î
	 */
	public static enum SortField {
		/**
		 * ²»ÅÅÐò
		 */
		None("createDate"), //
		goodsCode("goodsCode"), bomNo("bomNo");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

}
