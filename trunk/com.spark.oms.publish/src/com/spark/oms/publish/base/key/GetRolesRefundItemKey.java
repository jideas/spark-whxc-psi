package com.spark.oms.publish.base.key;

import com.spark.oms.publish.base.key.LimitKey.SortType;


public class GetRolesRefundItemKey {
	
	private String serachkey;
	
	private SortField sortField;
	
	/**
	 * 排序方式
	 */
	protected SortType sortType;
	
	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public String getSerachkey() {
		return serachkey;
	}

	public void setSerachkey(String serachkey) {
		this.serachkey = serachkey;
	}
	
	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}



	/**
	 * 排序对应字段
	 */
	public static enum SortField {
		
		roleCode("rolecode"),
		roleName("rolename"),
		createDate("createDate");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}
}
