package com.spark.oms.publish.receipt.key;

import com.spark.oms.publish.SortType;

/**
 * 获取客户未开票记录列表 GetTenantsUnDrawFreeItemKey & UnDrawBillItem
 */
public class GetTenantsUnDrawFreeItemKey {
	
	public static enum SortField {
		
		TENANTSNAME("un.TENANTSNAME"),//租户名称
		drawAmount("en.drawAmount"),//已经开票金额
		unDrawAmount("un.unDrawAmount");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}

	private String serachkey;

	private SortField sortField;

	private SortType sortType;

	public String getSerachkey() {
		return serachkey;
	}

	public void setSerachkey(String serachkey) {
		this.serachkey = serachkey;
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
}