package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 发送短信 主表，逻辑时分，实则 Send + SendItem = oms_messageSentLog(发送短信流水);
 * 
 *
 */
public interface Send {

	/**
	 * 短信id(收件箱中记录id)	
	 * @return
	 */
	public GUID getSMSRECID();
	
	/**
	 * 发送时间，同一批次提交保存的时间是相同的
	 * @return
	 */
	public long getSentTime();
	
	/**
	 * 租户Id
	 * @return
	 */
	public GUID getTenantsRECID();
	
	/**
	 * 租户名称
	 * @return
	 */
	public String getTenantsName();
	
	/**
	 * 短信通道编码
	 * @return
	 */
	public String getChannelRECID();
	
	/**
	 * 短信通道
	 * @return
	 */
	public String getChannelName();
	
	/**
	 * 短信发送明细
	 * @return
	 */
	public SendItem[] getSendItems();
}
