package com.spark.oms.publish.message.key;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;

/**
 * 可用于外围接口
 * 
 * 获取短信发送情况：发送成功条数，失败条数，发送金额
 * 
 * 此方法需要斟酌：时间延迟
 */
public class GetMessagesSendItemKey {
	/**
	 * 发送日期
	 */
	private Date sendDate;
	
	/**
	 * 租户Id
	 */
	private GUID tenantsId;

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	
	
}
