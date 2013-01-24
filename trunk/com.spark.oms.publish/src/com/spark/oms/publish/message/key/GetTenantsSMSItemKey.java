package com.spark.oms.publish.message.key;

import com.spark.oms.publish.SortType;

/**
 * 短信流水
 *
 */
public class GetTenantsSMSItemKey {
	
	public static enum SortField {
		
		TenantsName("TenantsName"),
		Charge("Cost");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}
	
	private SortField sortField;
	private SortType sortType;
	
	
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
	 * 开始日期
	 */
	private long startDate;
	
	/**
	 * 结束日期
	 */
	private long endDate;
	
	/**
	 * 通道标识
	 */
	private String channelId;

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	
	
	

}
