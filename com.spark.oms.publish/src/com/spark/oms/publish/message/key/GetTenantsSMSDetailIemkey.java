package com.spark.oms.publish.message.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ������ˮ��ϸ
 */
public class GetTenantsSMSDetailIemkey {
	/**
	 * ��ʼ����
	 */
	private long startDate;
	
	/**
	 * ��������
	 */
	private long endDate;
	
	/**
	 * ͨ����ʶ
	 */
	private String channelId;
	
	/**
	 * �⻧Id
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
