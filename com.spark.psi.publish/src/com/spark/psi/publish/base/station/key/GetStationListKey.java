package com.spark.psi.publish.base.station.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.SortType;

public class GetStationListKey extends LimitKey {

	private boolean queryAll;

	private SortField sortField = SortField.None;

	public GetStationListKey() {
		super(0, 20, false);
		this.sortType = SortType.Desc;
	}

	public GetStationListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	public SortField getSortField() {
		return sortField;
	}

	public boolean isQueryAll() {
		return queryAll;
	}

	public void setQueryAll(boolean queryAll) {
		this.queryAll = queryAll;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * 排序字段
	 */
	public static enum SortField {
		/**
		 * 不排序
		 */
		None("createDate"), //
		Number("stationNo"),
		/** 名称 */
		ShortName("namePY"), //
		/** 地址 */
		Address("Address"), //
		/** 负责人 */
		Manager("Manager"), //
		/** 联系电话 */
		MobileNo("MobileNo");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}

}
