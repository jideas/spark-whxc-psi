package com.spark.oms.publish.message.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 短信流水明细
 */
public class GetTenantsSMSDetailIemkey {
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
	
	/**
	 * 租户Id
	 */
	private GUID tenantsId;

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

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
}
